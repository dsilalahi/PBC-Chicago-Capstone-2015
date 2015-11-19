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
