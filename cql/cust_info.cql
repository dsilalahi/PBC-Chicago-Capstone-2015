-- add customer name to the receipts table
alter TABLE retail.receipts add 
       customer_name text;

-- add customre zip to the receipts table
alter TABLE retail.receipts add
       customer_zip text;

-- create customer_details table
CREATE TABLE IF NOT EXISTS retail.customer_details(
	customer_name text,
	customer_zip text,
	customer_address text,
	primary key((customer_name),customer_zip)
);
