CREATE TABLE customer (
    customer_id int NOT NULL,
    customer_name varchar(255) NOT NULL,
    customer_number bigint NOT NULL,
    address varchar(255) NOT NULL,
    gender varchar(255) NOT NULL,
    PRIMARY KEY (customer_id)
);

CREATE TABLE inventory (
    skuid int NOT NULL,
    product_name varchar(255) NOT NULL,
    product_label varchar(255) NOT NULL,
    inventoryonhand int NOT NULL,
    minqtyreq int NOT NULL,
    price double NOT NULL,
    PRIMARY KEY (skuid)
);

CREATE TABLE vendor (
    vendorid int NOT NULL,
    vendorname varchar(255) NOT NULL,
    vendorcontactno bigint NOT NULL,
    vendoremail varchar(255) NOT NULL,
    vendorusername varchar(255) NOT NULL,
    vendoraddress varchar(255) NOT NULL,
    PRIMARY KEY (vendorid)
);


insert into customer(customer_id,customer_name,customer_number,address,gender) values(1001,'Virat',10,'Ariadaha','Male');
insert into customer(customer_id,customer_name,customer_number,address,gender) values(1002,'Pujara',7,'Kolkata','Male');
insert into customer(customer_id,customer_name,customer_number,address,gender) values(1003,'Dinesh',11,'Delhi','Male');
insert into customer(customer_id,customer_name,customer_number,address,gender) values(1004,'Dhoni',18,'Ranchi','Male');
insert into customer(customer_id,customer_name,customer_number,address,gender) values(1005,'Anushka',100,'Delhi','Female');

insert into inventory(skuid,product_name,product_label,inventoryonhand,minqtyreq,price) values(10001,'Moto G5 Plus','1',100,10,12000);
insert into inventory(skuid,product_name,product_label,inventoryonhand,minqtyreq,price) values(10002,'Apple 8','1',100,10,64000);
insert into inventory(skuid,product_name,product_label,inventoryonhand,minqtyreq,price) values(10003,'RedMi','2',100,10,11000);
insert into inventory(skuid,product_name,product_label,inventoryonhand,minqtyreq,price) values(10004,'Samsung Galaxy','4',100,10,22000);

insert into vendor(vendorid,vendorname,vendorcontactno,vendoremail,vendorusername,vendoraddress) values(10001,'Vendor_001',1234567890,'vendor_1@gmail.com','vendor_1','Kolkata');
insert into vendor(vendorid,vendorname,vendorcontactno,vendoremail,vendorusername,vendoraddress) values(10002,'Vendor_002',1234567810,'vendor_2@gmail.com','vendor_2','Delhi');
insert into vendor(vendorid,vendorname,vendorcontactno,vendoremail,vendorusername,vendoraddress) values(10003,'Vendor_003',1234567820,'vendor_3@gmail.com','vendor_3','Mumbai');




