/**
 * Author:  baihu868
 * INFO202 2019
 * schema.sql
 */

create table Product (
    productID varchar(10),
    name varchar(10),
    description varchar(50),
    category varchar(10),
    listPrice decimal(5,2),
    quantityInStock integer(10),
    constraint Product_PK primary key (productID)
);

create table Customer (
    customerID bigint auto_increment(1000),
    username varchar(10) not null unique,
    firstName varchar(10) not null,
    surName varchar(10) not null,
    emailAddress varchar(30) not null,
    shippingAddress varchar(30) not null,
    passWord varchar(10) not null,
    constraint Customer_PK primary key (customerID)
);


create table Sale ( 
    customerID bigint auto_increment(1000),
    saleID bigint auto_increment(1000),
    date DATE not null,
    status varchar(50),
    constraint Sale_PK primary key (saleID),
    constraint Sale_Customer_FK foreign key (customerID) references Customer
);

create table SaleItem (
    productID varchar(10),
    saleID bigint auto_increment(1000),
    quantityPurchased integer,
    salePrice decimal(5,2),
    primary key (saleID, productID),
    constraint Sale_Product_FK foreign key (productID) references Product,
    constraint Sale_Item_FK foreign key (SaleID) references Sale
);
