/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  tylan825
 * Created: 9/08/2019
 */

Create table Products (
productID integer not null,
firstname varchar(50) not null,
description varchar(50),
category varchar(20) not null,
stockQuantity integer not null,
listPrice decimal not null,
constraint Products_PK primary key (productID)
);

Create table Customers (
customerid bigint auto_increment,
username varchar(50) not null,
firstName varchar(50) not null,
surname varchar(50) not null,
password varchar(50) not null,
shippingAddress varchar(50) not null,
emailAddress varchar(50) not null,
constraint Customers_PK primary key (username)
);


"insert into customers (CustomerID, Username, FirstName, Surname, Password, ShippingAddress, EmailAddress) values (?,?,?,?,?,?)";
