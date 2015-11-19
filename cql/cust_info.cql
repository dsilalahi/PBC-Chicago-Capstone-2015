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
