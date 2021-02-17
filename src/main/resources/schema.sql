CREATE DATABASE eccomerce;

create table customer(
customer_id INT primary key,
name varchar(250),
address varchar(250),
mobile_number INT(12)
);

create table cart(
cartid INT primary key,
user_id INT foreign key REFERENCES(customer_id),
prod_id  INT  foreign key REFERENCES(prodid),
products VARCHAR(250),
totalvalue float(100)
);

create products(
prodid INT primary key,
product_name varchar(250),
product_description varchar(250)
);