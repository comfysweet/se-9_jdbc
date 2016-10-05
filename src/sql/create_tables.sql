--DROP TABLE PAYMENT1.Order_Product;
--DROP TABLE PAYMENT1.customer_order;
--DROP TABLE PAYMENT1.CUSTOMER;
--DROP TABLE PAYMENT1.PRODUCT;


CREATE TABLE PAYMENT1.CUSTOMER (
  customer_id NUMBER(20),
  first_name  VARCHAR2(30) NOT NULL,
  last_name   VARCHAR2(30) NOT NULL,
  card_number NUMBER(30) UNIQUE NOT NULL,
  CONSTRAINT PK_Customer_ID PRIMARY KEY (customer_id)
);

CREATE TABLE PAYMENT1.customer_order (
  order_id    NUMBER(20),
  date_order  DATE NOT NULL,
  customer_id NUMBER(20),
  CONSTRAINT PK_Order_ID PRIMARY KEY (order_id),
  CONSTRAINT FK_Order_CustomerID FOREIGN KEY (customer_id) REFERENCES
  PAYMENT1.CUSTOMER (customer_id)
);

CREATE TABLE PAYMENT1.PRODUCT (
  product_id   NUMBER(20),
  name_product VARCHAR2(50)   NOT NULL,
  cost_product NUMBER(10, 2) NOT NULL,
  CONSTRAINT PK_Product_ID PRIMARY KEY (product_id)
);

CREATE TABLE PAYMENT1.Order_Product (
  ID         NUMBER(20),
  order_id   NUMBER(20),
  product_id NUMBER(20),
  CONSTRAINT PK_ID PRIMARY KEY (ID),
  CONSTRAINT FK_ID_OrderID FOREIGN KEY (order_id) REFERENCES
  PAYMENT1.customer_order (order_id),
  CONSTRAINT FK_ID_ProductID FOREIGN KEY (product_id) REFERENCES
  PAYMENT1.PRODUCT (product_id)
);


