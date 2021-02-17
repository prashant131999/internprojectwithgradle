CREATE DATABASE db;

CREATE TABLE customer(
customer_id INT PRIMARY KEY,
name VARCHAR(250),
address VARCHAR(250),
mobile_number INT(12)
);

CREATE TABLE cart(
cartid INT NOT NULL,
customer_id INT,
prodid INT,
products VARCHAR(250) NOT NULL,
date_created TIMESTAMP,
date_modified TIMESTAMP,
totalvalue FLOAT(50),
PRIMARY KEY (cartid),
FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
FOREIGN KEY (prodid)  REFERENCES products(prodid)
);

CREATE TYPE categories AS ENUM('electronics','books','cloths');

CREATE TABLE products(
prodid INT NOT NULL,
product_name VARCHAR(250),
product_description categories,
product_cost float(50),
PRIMARY KEY(prodid)
);