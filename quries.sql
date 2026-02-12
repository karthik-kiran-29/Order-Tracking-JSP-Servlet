CREATE DATABASE ordersystem;

USE ordersystem;

CREATE TABLE ORDER_TB(
	OrderId VARCHAR(50) NOT NULL,
	CustomerName VARCHAR(50) NOT NULL,
	OrderItem VARCHAR(50) NOT NULL,
	Order_Date DATE NOT NULL,
	Delivery_Date DATE,
	Status VARCHAR(20),
	Remarks VARCHAR(100)
);

