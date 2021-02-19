CREATE DATABASE db;

CREATE TABLE customer(
customer_id INT PRIMARY KEY,
first_name VARCHAR(250),
last_name  VARCHAR(250),
address VARCHAR(250),
mobile_number VARCHAR(10),
date_of_birth DATE,
date_created TIMESTAMP,
date_last_modified TIMESTAMP
);

CREATE TABLE order(
order_id INT NOT NULL,
customer_id INT,
prod_id INT,
product_list VARCHAR(250) NOT NULL,
date_created TIMESTAMP,
date_delivered TIMESTAMP,
totalvalue FLOAT(50),
PRIMARY KEY (cartid),
FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
FOREIGN KEY (prodid)  REFERENCES products(prodid)
);

CREATE TYPE categories AS ENUM('electronics','books','cloths');

CREATE TABLE products(
prod_id INT NOT NULL,
product_name VARCHAR(250),
product_description categories,
product_cost float(50),
PRIMARY KEY(prod_id)
);