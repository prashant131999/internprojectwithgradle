CREATE DATABASE db;

CREATE TABLE customer(
customer_id UUID PRIMARY KEY,
first_name VARCHAR(250),
last_name  VARCHAR(250),
address VARCHAR(250),
mobile_number VARCHAR(10) NOT NULL UNIQUE,
email VARCHAR(40) NOT NULL UNIQUE,
date_of_birth VARCHAR(10),
date_created TIMESTAMP ,
date_last_modified TIMESTAMP
);



CREATE TYPE categories AS ENUM('electronics','books','cloths');

CREATE TABLE products(
prod_id UUID PRIMARY KEY,
product_name VARCHAR(250),
prod_quantity INT NOT NULL,
product_cost float(50)
);

product_description VARCHAR(250);