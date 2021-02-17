ALTER TABLE customer DROP COLUMN mobile_number;
INSERT INTO customer VAlUES(1,'prashant','meerut','6923923793');
INSERT INTO customer VAlUES(2,'sahrawat','delhi','2429482048');
INSERT INTO customer VAlUES(3,'gupta','bangalore','8204824080');
INSERT INTO customer VAlUES(4,'agarwal','mumbai','9292942442');
INSERT INTO products VALUES(21,'sherlock holmes','books',463.54);
INSERT INTO products VALUES(22,'laptop','electronics',20000.7);
INSERT INTO products VALUES(23,'tshirt','cloths',15000.3);
INSERT INTO cart     VALUES(11,1,21,'sherlock holmes',current_timestamp,current_timestamp,463.54);
INSERT INTO cart     VALUES(12,2,22,'laptop',current_timestamp,current_timestamp,20463.54);
INSERT INTO cart     VALUES(13,3,23,'tshirt',current_timestamp,current_timestamp,35463.54);


SELECT * FROM customer;
SELECT * FROM products;
SElECT * FROM cart;
ALTER TABLE customer ADD mobile_number VARCHAR(15);