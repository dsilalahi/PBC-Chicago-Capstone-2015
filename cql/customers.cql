CREATE TABLE IF NOT EXISTS retail.receipts(
    customer_name text,
    email set<text>,
    address text,
    state text,
    country text,
    postal_code text,
    PRIMARY KEY(customer_name, state, zip)
);