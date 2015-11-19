The Black Friday project contains 4 major parts.

The first is in the cql directory that sets up the database schema for the entire project.

The second part is a sample Spark job

The third  part is a sample Solr schema with a script to post it

The fourth part is the web project in the web directory that is a simple Python / Flask project that runs against a local DSE. This expects that you have a local DSE setup and running.

The fifth is a cash-register simulator implemented with JMeter

## Mac Setup ##

1 - Install [brew](http://brew.sh/)   - The default mac python won't work

2 - install wget:
```
  brew install wget
```

2 - install brew's version of python:
```
  brew install python
```

3 - verify you can run python:  

```
      python
      exit()
```

2 - install pip:
```
  brew install pip
```

## All platforms - for manual setup ##

4 Install Python dependencies:

```
pip install flask
pip install blist
pip install cassandra-driver
pip install requests
```

7 - Download and unzip [google visualization](https://google-visualization-python.googlecode.com/files/gviz_api_py-1.8.2.tar.gz)

8 - cd into the google visualization directory and run:
```
Installing the library:
  python ./setup.py install
  (You might need root privileges to do this)

Testing the library:
  python ./setup.py test
```

## Start here with the VM ##

11 - Import the seed data:
```
  cd cql
  cqlsh -f retail.cql
  cqlsh -f import.cql
```

10 - Import the sample solr index
```
   cd solr
   ./add-schema.sh products_by_id
```
   
9 - Run the primary project which starts up the Flask Web Project:
```
  cd web
  ./run &
```
11 - Verify that the project is running locally by going to [http://localhost:5001/](http://localhost:5001/)

Try out the solr search and the product lookup. Some of the other pages are blank, as
they are populated in the following steps. You may need to peek in the database to find 
valid product ids.

10 - Simulate the cash registers
```
   cd jmeter
   jmeter -n -t scan.jmx
```
You can also start jmeter without the -n option to run it in the foreground
This will insert a significant amount of data, so run it sparingly.

12 - Rollup the data by submitting the 
```

11 - Setup DSE / Cassandra.  Under the cql directory is the schema for the database.  It's assumed that cqlsh is in your path.  Import it using:
```
  cd cql
  cqlsh -f retail.cql
```


### Project Overview ###

The directory web/routes has the basic files for the web project.  It uses the familiar paradigm of Ruby on Rails or Play Framework of having a routes file the defines what happens when the user navigates to different URL's.

route.py - This defines the basic index for the root URL of the project.  It simply generates the html in that method.
```
  @web_api.route('/')
```

rest.py - This defines the rest endpoints for the project.  As one example navigate to [http://localhost:5000/api/paging/system/compaction_history/](http://localhost:5000/api/paging/system/compaction_history/).  This calls the following route in rest.py:
```
  @rest_api.route('/paging/<keyspace>/<table>/')
```
It takes the parameter from the method out of the URL path, so what every keyspace and table and path are passed in will be used for this method.  It then calls a basic select query based on that keyspace and table:
```
  'SELECT * FROM %s.%s' % (keyspace, table)
```

The templates under web/templates are basic jinja2 templates that are similiar to JSP's.  Everything between {%  %}  is part of the script for that template.  Then the content for the page gets pulled in where the {{ content }} is defined.


### Capstone Tasks

#### Solr Excercise

##### Creating the Solr Index

Create an index on retail.receipts casasndra table
```
dsetool create_core retail.receipts generateResources=true reindex=true 
```
Ran the query using Solr Rest API and confirmed that we got the results as expected
```http
http://localhost:8983/solr/retail.receipts/select?q=product_name%3ALG*&wt=json&indent=true
```

```
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

Inorder to reuse /retail/solr/add-schema.sh we got the XML file from the Solr Web interface and copied over into the /retail/solr directory
