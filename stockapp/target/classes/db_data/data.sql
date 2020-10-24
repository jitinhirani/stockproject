--CREATE SCHEMA stockdb;

CREATE TABLE stockitemtype (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE department (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE category (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE source (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE stocktype (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE centre (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE stockitem (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  dateofEntry DATE DEFAULT NULL,
  dateofreceipt DATE DEFAULT NULL,
  letternumber VARCHAR(250) NOT NULL,
  letterdate DATE DEFAULT NULL,
  quantity DECIMAL NOT NULL,
  size VARCHAR(250) DEFAULT NULL,
  cost DECIMAL DEFAULT NULL,
  subcategory1 VARCHAR(250) DEFAULT '',
  subcategory2 VARCHAR(250) DEFAULT '',
  subcategory3 VARCHAR(250) DEFAULT '',
  sentto VARCHAR(250) DEFAULT '',
  receivedfrom VARCHAR(250) DEFAULT '',
  itemtype INT NOT NULL,
  FOREIGN KEY (itemtype) REFERENCES stockitemtype(id),
  source INT,
  FOREIGN KEY (source) REFERENCES source(id),
  stocktype INT,
  FOREIGN KEY (stocktype) REFERENCES stocktype(id),
  department INT,
  FOREIGN KEY (department) REFERENCES department(id),
  category INT,
  FOREIGN KEY (category) REFERENCES category(id),
  REMARKS nvarchar(2000)
);

commit;

INSERT INTO stockitemtype(name) VALUES ('REGULAR');
INSERT INTO stockitemtype(name) VALUES ('LOAN');

INSERT INTO category(name) VALUES ('Electric');
INSERT INTO category(name) VALUES ('Furniture');
INSERT INTO category(name) VALUES ('Kitchen');
INSERT INTO category(name) VALUES ('Office Equipments');
INSERT INTO category(name) VALUES ('Expenses');
INSERT INTO category(name) VALUES ('Computer');
INSERT INTO category(name) VALUES ('Tools & Machinery');
INSERT INTO category(name) VALUES ('Pandal Items');
INSERT INTO category(name) VALUES ('Clothing & Furnishing');
INSERT INTO category(name) VALUES ('Nal');
INSERT INTO category(name) VALUES ('Vehicle');
INSERT INTO category(name) VALUES ('Misc');
INSERT INTO category(name) VALUES ('Eng');
INSERT INTO category(name) VALUES ('Refrigerator, A.C. & Coolers');
INSERT INTO category(name) VALUES ('Hygiene & Sanitation');
INSERT INTO category(name) VALUES ('Motor Vehicle');
INSERT INTO category(name) VALUES ('Sound');

INSERT INTO department(name) values('Accommodation');
INSERT INTO department(name) values('Adam Block-Main Office');
INSERT INTO department(name) values('Banner & Painting');
INSERT INTO department(name) values('BookStall');
INSERT INTO department(name) values('Canteen');
INSERT INTO department(name) values('Carpentary');
INSERT INTO department(name) values('CCTV');
INSERT INTO department(name) values('Central Store');
INSERT INTO department(name) values('Chhabeel');
INSERT INTO department(name) values('Coupon Stall');
INSERT INTO department(name) values('Darshan Sewa');
INSERT INTO department(name) values('Dispensary');
INSERT INTO department(name) values('Electric');
INSERT INTO department(name) values('Engineering');
INSERT INTO department(name) values('Fire');
INSERT INTO department(name) values('Horticulture');
INSERT INTO department(name) values('Langar');
INSERT INTO department(name) values('Luggage');
INSERT INTO department(name) values('Maintenance');
INSERT INTO department(name) values('Nal Sewa');
INSERT INTO department(name) values('Naamdaan');
INSERT INTO department(name) values('Nursery');
INSERT INTO department(name) values('Pandal');
INSERT INTO department(name) values('Security');
INSERT INTO department(name) values('Sanitation');
INSERT INTO department(name) values('Sewa Samiti');
INSERT INTO department(name) values('Shamiana');
INSERT INTO department(name) values('Sound');
INSERT INTO department(name) values('TCDC');
INSERT INTO department(name) values('Telecom');
INSERT INTO department(name) values('Traffic');
INSERT INTO department(name) values('TV');
INSERT INTO department(name) values('Workshop');

INSERT INTO source(name) VALUES ('Purchase');
INSERT INTO source(name) VALUES ('Sewa');
INSERT INTO source(name) VALUES ('Dera');
INSERT INTO source(name) VALUES ('Transfer');
INSERT INTO source(name) VALUES ('Replacement');
INSERT INTO source(name) VALUES ('Fabricated');
INSERT INTO source(name) VALUES ('Condemned');
INSERT INTO source(name) VALUES ('Received');
INSERT INTO source(name) VALUES ('Written Off');
INSERT INTO source(name) VALUES ('Loan');
INSERT INTO source(name) VALUES ('Regularised');
INSERT INTO source(name) VALUES ('Consumed');

INSERT INTO stocktype(name) VALUES ('Received');
INSERT INTO stocktype(name) VALUES ('Loan');
INSERT INTO stocktype(name) VALUES ('Transfer');
INSERT INTO stocktype(name) VALUES ('Written Off');
INSERT INTO stocktype(name) VALUES ('Underground');
INSERT INTO stocktype(name) VALUES ('Open');

INSERT INTO centre(name) VALUES ('Indore');
INSERT INTO centre(name) VALUES ('Kila Road');
INSERT INTO centre(name) VALUES ('Bicholi Hapsi');
INSERT INTO centre(name) VALUES ('Gandhi Nagar');
INSERT INTO centre(name) VALUES ('Sukhliya');
INSERT INTO centre(name) VALUES ('Dwarkapuri');
INSERT INTO centre(name) VALUES ('Manglia-Mhow');
INSERT INTO centre(name) VALUES ('Pithampur-Mhow');
INSERT INTO centre(name) VALUES ('Hatod-Mhow');
INSERT INTO centre(name) VALUES ('Mandsaur');
INSERT INTO centre(name) VALUES ('Nagpur');
INSERT INTO centre(name) VALUES ('Raipur');
INSERT INTO centre(name) VALUES ('Ahmedabad');
INSERT INTO centre(name) VALUES ('Delhi');
INSERT INTO centre(name) VALUES ('Mumbai');
INSERT INTO centre(name) VALUES ('Biaora');
INSERT INTO centre(name) VALUES ('Potlod');
INSERT INTO centre(name) VALUES ('Kanwan-Mhow');
INSERT INTO centre(name) VALUES ('Sanawad');
INSERT INTO centre(name) VALUES ('Rajgarh');
INSERT INTO centre(name) VALUES ('Pivday');
INSERT INTO centre(name) VALUES ('Ratlam');
INSERT INTO centre(name) VALUES ('Gautampura');

commit;