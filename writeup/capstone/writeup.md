### Capstone Tasks

#### Team
* Hamdan Ahmad
* Dohar Silalahi
* Ravi Akkineni

#### Solr Excercise

##### Creating the Solr Index

Create an index on retail.receipts cassandra table
```sh
dsetool create_core retail.receipts generateResources=true reindex=true 
```

Receipts XML
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<schema name="autoSolrSchema" version="1.5">
    <types>
        <fieldType name="string" class="solr.StrField"/>
        <fieldType name="text" class="solr.TextField">
            <analyzer>
                <tokenizer class="solr.StandardTokenizerFactory"/>
                <filter class="solr.LowerCaseFilterFactory"/>
            </analyzer>
        </fieldType>
        <fieldType name="int"     class="solr.IntField"/>
        <fieldType name="uuid"    class="solr.UUIDField"/>
        <fieldType name="decimal" class="com.datastax.bdp.search.solr.core.types.DecimalStrField"/>
        <fieldType name="long"    class="solr.SortableLongField"/>
        <fieldType name="float"   class="solr.SortableFloatField"/>
        <fieldType name="date"    class="org.apache.solr.schema.TrieDateField"/>
    </types>

    <fields>
        <field indexed="true" multiValued="false" name="receipt_id" stored="true" type="long"/>
        <field indexed="true" multiValued="false" name="product_name" stored="true" type="text"/>
        <field indexed="true" multiValued="false" name="product_id" stored="true" type="string"/>
        <field indexed="true" multiValued="false" name="unit_price" stored="true" type="decimal"/>
        <field indexed="true" multiValued="false" name="scan_id" stored="true" type="uuid"/>
        <field indexed="true" multiValued="false" name="total" stored="true" type="decimal"/>
        <field indexed="true" multiValued="false" name="customer_name" stored="true" type="text"/>
        <field indexed="true" multiValued="false" name="customer_zip" stored="true" type="text"/>
        <field indexed="true" multiValued="false" name="quantity" stored="true" type="int"/>
    </fields>

    <defaultSearchField>product_name</defaultSearchField>
    <uniqueKey>(receipt_id, scan_id)</uniqueKey>
</schema>
```

Add/reload solr index by running:
```sh
./add-schema.sh -r receipts
```

Ran the query using Solr Rest API and confirmed that we got the results as expected
```http
http://localhost:8983/solr/retail.receipts/select?q=product_name%3ALG*&wt=json&indent=true
```

```json
{
  "responseHeader": {
    "status": 0,
    "QTime": 47
  },
  "response": {
    "numFound": 5523,
    "start": 0,
    "docs": [
      {
        "_uniqueKey": "[\"1447945835509\",\"b0bbcbab-8ecf-11e5-8711-8b496c707234\"]",
        "quantity": 4,
        "receipt_id": 1447945835509,
        "unit_price": "29.99",
        "product_name": "LG 22MT44D LED display",
        "total": "119.96",
        "product_id": "22MT44D",
        "scan_id": "b0bbcbab-8ecf-11e5-8711-8b496c707234"
      },
      {
        "_uniqueKey": "[\"1447945806738\",\"9e6323e1-8ecf-11e5-8711-8b496c707234\"]",
        "quantity": 4,
        "receipt_id": 1447945806738,
        "unit_price": "119.99",
        "product_name": "LG 55LW5500 LED TV",
        "total": "479.96",
        "product_id": "55LW5500",
        "scan_id": "9e6323e1-8ecf-11e5-8711-8b496c707234"
      },
      {
        "_uniqueKey": "[\"1447949366984\",\"e896fbf6-8ed7-11e5-8711-8b496c707234\"]",
        "quantity": 1,
        "receipt_id": 1447949366984,
        "unit_price": "79.99",
        "product_name": "LG 32LB520B LED TV",
        "total": "79.99",
        "product_id": "32LB520B",
        "scan_id": "e896fbf6-8ed7-11e5-8711-8b496c707234"
      },
```

In order to reuse /retail/solr/add-schema.sh we got the XML file from the Solr Web interface and copied over into the /retail/solr directory


In the web-python project, we created/updated the following files to demo the solr search for receipts
- web-python/routes/web.py (controller that pulls data from the data source)
- web-python/templates/search_receipts_list.jinja2 (a new jinja file in the view layer)


#### Spark Excercise

##### Fraud detection job
In order to complete the requested task we had to create a spark job in order to load the data into a table for the web application to consume.

To start off we created the table the data is stored on

```sql
CREATE TABLE retail.credit_card_fraud_list (
   credit_card_number bigint,
   num_distinct_state int,
   PRIMARY KEY (credit_card_number)
); 
```

After which the spark code we developed to load the data
```java
val receipts_by_creditcard = sc.cassandraTable("retail","receipts_by_credit_card").select("receipt_id","credit_card_number","store_id","receipt_timestamp").keyBy(row => row.getString("store_id"))
val stores =  sc.cassandraTable("retail","stores").select("store_id","state").keyBy(row => row.getString("store_id"))
val creditcard_by_store = receipts_by_creditcard.join(stores).persist()

//get the credit card that were being used fraudulently (use int two different states)
val creditcard_fraud= creditcard_by_store.map{ case (store_id,(result1,result2)) => (result1.getString("credit_card_number"),result2.getString("state"))}.distinct.countByKey().filter{ case (k,v) => v > 1}
sc.parallelize(creditcard_fraud.toList).saveToCassandra("retail","credit_card_fraud_list",SomeColumns("credit_card_number","num_distinct_state"))
```

##### Spark Fraud Detection


```sql
cqlsh:retail> select credit_card_number,store_id from retail.receipts_by_credit_card where credit_card_number=5383439216820189;

 credit_card_number | store_id
--------------------+----------
   5383439216820189 |       76
   5383439216820189 |      235
   5383439216820189 |      210
   5383439216820189 |      356
   5383439216820189 |      218
   5383439216820189 |      354
   5383439216820189 |      152
   5383439216820189 |       88
   5383439216820189 |      122
   5383439216820189 |      336
   5383439216820189 |      263
   5383439216820189 |      301
   5383439216820189 |      127

(13 rows)
cqlsh:retail> select state from retail.stores where store_id=76;

 state
-------
    CT

(1 rows)
cqlsh:retail> select state from retail.stores where store_id=263;

 state
-------
    AL

(1 rows)
cqlsh:retail> select state from retail.stores where store_id=88;

 state
-------
    NY
```

Finally in order to display the data we added the following code in the index.jinja2 template

```html
<li>
	<a href="/gcharts/Table/?url=/api/simplequery&q=select * from credit_card_fraud_list limit 100&order_col=credit_card_number">
	    Google Charts: Table of Fraudulent Credit Card
	</a>
</li>
```

##### State to State Fraud Analysis

Again we started out by creating the underlying table that Spark would populate and the web application would use
```sql
CREATE TABLE retail.state_to_state_fraud (
   state1 text,
   state2 text,   
   num_fraud int,
   PRIMARY KEY (state1)
); 
```

This was followed by the Spark Job to populate the data
```
val receipts_by_creditcard = sc.cassandraTable("retail","receipts_by_credit_card").select("receipt_id","credit_card_number","store_id","receipt_timestamp").keyBy(row => row.getString("store_id"))
val stores =  sc.cassandraTable("retail","stores").select("store_id","state").keyBy(row => row.getString("store_id"))
val creditcard_by_store = receipts_by_creditcard.join(stores).persist()
//gets the number of fraud occuring between any two states
val creditcard_by_store_unpacked = creditcard_by_store.map({case (store_id,(result1, result2)) => (result1.getString("credit_card_number"), result2.getString("state"))})
val creditcard_by_store_self_join = creditcard_by_store_unpacked.join(creditcard_by_store_unpacked)
val state2state_fraud = creditcard_by_store_self_join.map{case (store_id,(state1, state2)) => ((state1,state2),store_id)}.filter{ case ((state1,state2),store_id) => state1 != state2}.countByKey().filter{ case (k,v) => v > 1}.map{ case ((state1,state2),num_fraud) => (state1,state2 + "_",num_fraud)}
val state2state_fraud_filter = state2state_fraud.filter( t => filter(t._3 > 100))
sc.parallelize(state2state_fraud_filter.toList).saveToCassandra("retail","state_to_state_fraud",SomeColumns("state1","state2","num_fraud"))
```

And lastly we updated index.jinja2 template to be able to use the link

```html
<li>
    <a href="/gcharts/Sankey/?options={height:500,width:1000,sankey:{node:{colors:['%23a6cee3', '%23b2df8a', '%23fb9a99', '%23fdbf6f','%23cab2d6', '%23ffff99', '%231f78b4', '%2333a02c']},link:{colorMode:'gradient',colors:['%23a6cee3', '%23b2df8a', '%23fb9a99', '%23fdbf6f','%23cab2d6', '%23ffff99', '%231f78b4', '%2333a02c']}}}&url=/api/simplequery&q=select state1,state2,num_fraud from state_to_state_fraud">
        Google Charts: Fraudulent Credit Usage Between States
    </a>
</li>
```

#### Data Model Excericse:

Add customer_name and Customer_Zip to receipt: Modified receipt table to add customer_name and customer_zip to satisfy this.

Save Customer_Details: Added customer_deatails(customer_name, Customer_zip,customer_address) table to allow saving customer info in different table when customer added to receipt table. 

```
alter TABLE retail.receipts add 
       customer_name text;

alter table retail.receipts add
       customer_zip text;

create table if not exists retail.customer_details(
customer_name text,
customer_zip text,
customer_address text,
primary key((customer_name),customer_zip)
);
```

#### Modify Jmx file to load customer info:

1. Add two new CSV handles to load customer_name from 4000names.csv and Customer_zip from zicodes.csv.
2. modify scan-receipts insert script to include customer info using above random customer_name, customer_zip
3. Add new insert handle to insert into customer_detail table whenever new customer (customer_name,zip) in receipts table

JMX file location below:

```xml
/retail/jmeter# ls
         scans.jmx
```
```sql
cqlsh> select * from retail.customer_details limit 5;

 customer_name      | customer_zip | customer_address
--------------------+--------------+------------------
     Ehtel Murakami |        55787 |             null
       Kareem Crudo |        22150 |             null
 Mohammed Ebershoff |        43015 |             null
     Denisse Herter |        01109 |             null
    Jacklyn Scheets |        54455 |             null
    
    
    select * from retail.receipts limit 5;



 receipt_id    | scan_id                              | credit_card_number | credit_card_type | receipt_timestamp        | receipt_total | register_id | store_id | customer_name | customer_zip | product_id   | product_name                                       | quantity | total  | unit_price
---------------+--------------------------------------+--------------------+------------------+--------------------------+---------------+-------------+----------+---------------+--------------+--------------+----------------------------------------------------+----------+--------+------------
 1447975831933 | 8695af31-8f15-11e5-bcf9-8b496c707234 |      4929494253111 |             Visa | 2015-02-01 17:10:21+0000 |         589.9 |           6 |      164 |   Stan Totten |        84124 |       U3SP0E |              HP 5y Nbd Exch 95/75xx bal Mod FC SVC |        1 |  29.99 |      29.99
 1447975831933 | 86a05d91-8f15-11e5-bcf9-8b496c707234 |      4929494253111 |             Visa | 2015-02-01 17:10:21+0000 |         589.9 |           6 |      164 |   Stan Totten |        84124 |    714260130 |                     Massive Wall light 71426/01/30 |        4 | 279.96 |      69.99
 1447975831933 | 86aabdd1-8f15-11e5-bcf9-8b496c707234 |      4929494253111 |             Visa | 2015-02-01 17:10:21+0000 |         589.9 |           6 |      164 |   Stan Totten |        84124 |  DES-1210-52 |                  D-Link DES-1210-52 network switch |        3 | 179.97 |      59.99
 1447975831933 | 86b76801-8f15-11e5-bcf9-8b496c707234 |      4929494253111 |             Visa | 2015-02-01 17:10:21+0000 |         589.9 |           6 |      164 |   Stan Totten |        84124 | 33.TL701.006 |                     Acer 33.TL701.006 mounting kit |        2 |  99.98 |      49.99
 1447975830805 | 85eaa222-8f15-11e5-bcf9-8b496c707234 |      4716150002035 |             Visa | 2015-02-01 16:44:39+0000 |       1639.76 |          17 |      201 | Marlyn Nelder |        77354 |   ICFDS15IPS | Sony ICF-DS15iP Dock clock radio for iPod / iPhone |        3 | 119.97 |      39.99

    
```


 
