--CREATE DATABASE stockdb;

-- DELETE ALL ENTRIES FROM STOCKITEM TABLE ---- START
select min(id), max(id), count(*) from stockitem;
DELETE FROM STOCKITEM WHERE ID>0;
ALTER TABLE STOCKITEM ALTER COLUMN ID RESTART WITH 1;
-- DELETE ALL ENTRIES FROM STOCKITEM TABLE ---- END

-- update department set name='Medical' where name='Dispensary';
-- update department set name='Artwork' where name='Banner & Painting';

/*
DROP TABLE IF EXISTS billionaires;
 
CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);
 
INSERT INTO billionaires (first_name, last_name, career) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist'),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');
*/
-- REGULAR or LOAN Type
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

INSERT INTO stockitemtype(id, name) VALUES (1,'REGULAR');
INSERT INTO stockitemtype(id, name) VALUES (2,'LOAN');

INSERT INTO category(id, name) VALUES (1,'Electric');
INSERT INTO category(id, name) VALUES (2,'Furniture');
INSERT INTO category(id, name) VALUES (3,'Kitchen');
INSERT INTO category(id, name) VALUES (4,'Office Equipments');
INSERT INTO category(id, name) VALUES (5,'Expenses');
INSERT INTO category(id, name) VALUES (6,'Computer');
INSERT INTO category(id, name) VALUES (7,'Tools & Machinery');
INSERT INTO category(id, name) VALUES (8,'Pandal Items');
INSERT INTO category(id, name) VALUES (9,'Clothing & Furnishing');
INSERT INTO category(id, name) VALUES (10,'Nal');
INSERT INTO category(id, name) VALUES (11,'Vehicle');
INSERT INTO category(id, name) VALUES (12,'Misc');
INSERT INTO category(id, name) VALUES (13,'Eng');
INSERT INTO category(id, name) VALUES (14,'Refrigerator, A.C. & Coolers');
INSERT INTO category(id, name) VALUES (15,'Hygiene & Sanitation');
INSERT INTO category(id, name) VALUES (16,'Motor Vehicle');
INSERT INTO category(id, name) VALUES (17,'Sound');

INSERT INTO department(id, name) values(1, 'Accommodation');
INSERT INTO department(id, name) values(2, 'Adam Block-Main Office');
INSERT INTO department(id, name) values(3, 'Artwork');
INSERT INTO department(id, name) values(4, 'BookStall');
INSERT INTO department(id, name) values(5, 'Canteen');
INSERT INTO department(id, name) values(6, 'Carpentary');
INSERT INTO department(id, name) values(7, 'CCTV');
INSERT INTO department(id, name) values(8, 'Central Store');
INSERT INTO department(id, name) values(9, 'Chhabeel');
INSERT INTO department(id, name) values(10, 'Coupon Stall');
INSERT INTO department(id, name) values(11, 'Darshan Sewa');
INSERT INTO department(id, name) values(12, 'Medical');
INSERT INTO department(id, name) values(13, 'Electric');
INSERT INTO department(id, name) values(14, 'Engineering');
INSERT INTO department(id, name) values(15, 'Fire');
INSERT INTO department(id, name) values(16, 'Horticulture');
INSERT INTO department(id, name) values(17, 'Langar');
INSERT INTO department(id, name) values(18, 'Luggage');
INSERT INTO department(id, name) values(19, 'Maintenance');
INSERT INTO department(id, name) values(20, 'Nal Sewa');
INSERT INTO department(id, name) values(21, 'Naamdaan');
INSERT INTO department(id, name) values(22, 'Nursery');
INSERT INTO department(id, name) values(23, 'Pandal');
INSERT INTO department(id, name) values(24, 'Security');
INSERT INTO department(id, name) values(25, 'Sanitation');
INSERT INTO department(id, name) values(26, 'Sewa Samiti');
INSERT INTO department(id, name) values(27, 'Shamiana');
INSERT INTO department(id, name) values(28, 'Sound');
INSERT INTO department(id, name) values(29, 'TCDC');
INSERT INTO department(id, name) values(30, 'Telecom');
INSERT INTO department(id, name) values(31, 'Traffic');
INSERT INTO department(id, name) values(32, 'TV');
INSERT INTO department(id, name) values(33, 'Workshop');


INSERT INTO source(id, name) VALUES (1,'Purchase');
INSERT INTO source(id, name) VALUES (2,'Sewa');
INSERT INTO source(id, name) VALUES (3,'Dera');
INSERT INTO source(id, name) VALUES (4,'Transfer');
INSERT INTO source(id, name) VALUES (5,'Replacement');
INSERT INTO source(id, name) VALUES (6,'Fabricated');
INSERT INTO source(id, name) VALUES (7,'Condemned');
INSERT INTO source(id, name) VALUES (8,'Received');
INSERT INTO source(id, name) VALUES (9,'Written Off');
INSERT INTO source(id, name) VALUES (10,'Loan');
INSERT INTO source(id, name) VALUES (11,'Regularised');
INSERT INTO source(id, name) VALUES (12,'Consumed');

INSERT INTO stocktype(id, name) VALUES (1,'Received');
INSERT INTO stocktype(id, name) VALUES (2,'Loan');
INSERT INTO stocktype(id, name) VALUES (3,'Transfer');
INSERT INTO stocktype(id, name) VALUES (4,'Written Off');
INSERT INTO stocktype(id, name) VALUES (5,'Underground');
INSERT INTO stocktype(id, name) VALUES (6,'Open');

INSERT INTO centre(id, name) VALUES (1,'Indore Area');
INSERT INTO centre(id, name) VALUES (2,'Kila Road');
INSERT INTO centre(id, name) VALUES (3,'Bicholi Hapsi');
INSERT INTO centre(id, name) VALUES (4,'Gandhi Nagar');
INSERT INTO centre(id, name) VALUES (5,'Sukhliya');
INSERT INTO centre(id, name) VALUES (6,'Dwarkapuri');
INSERT INTO centre(id, name) VALUES (7,'Manglia-Mhow');
INSERT INTO centre(id, name) VALUES (8,'Pithampur-Mhow');
INSERT INTO centre(id, name) VALUES (9,'Hatod-Mhow');
INSERT INTO centre(id, name) VALUES (10,'Mandsaur');
INSERT INTO centre(id, name) VALUES (11,'Nagpur');
INSERT INTO centre(id, name) VALUES (12,'Raipur');
INSERT INTO centre(id, name) VALUES (13,'Ahmedabad');
INSERT INTO centre(id, name) VALUES (14,'Delhi');
INSERT INTO centre(id, name) VALUES (15,'Mumbai');
INSERT INTO centre(id, name) VALUES (16,'Biaora');
INSERT INTO centre(id, name) VALUES (17,'Potlod');
INSERT INTO centre(id, name) VALUES (18,'Kanwan-Mhow');
INSERT INTO centre(id, name) VALUES (19,'Sanawad');
INSERT INTO centre(id, name) VALUES (20,'Rajgarh');
INSERT INTO centre(id, name) VALUES (21,'Pivday');
INSERT INTO centre(id, name) VALUES (22,'Ratlam');
INSERT INTO centre(id, name) VALUES (23,'Gautampura');

-- INSERT INTO centre(id, name, area) VALUES (1,'Indore Area', NULL);
-- INSERT INTO centre(id, name, area) VALUES (2,'Kila Road', NULL);
-- INSERT INTO centre(id, name, area) VALUES (2,'Bicholi Hapsi', NULL);
-- INSERT INTO centre(id, name, area) VALUES (3,'Gandhi Nagar', NULL);
-- INSERT INTO centre(id, name, area) VALUES (4,'Sukhliya', NULL);
-- INSERT INTO centre(id, name, area) VALUES (5,'Dwarkapuri', NULL);
-- INSERT INTO centre(id, name, area) VALUES (6,'Manglia-Mhow', NULL);
-- INSERT INTO centre(id, name, area) VALUES (7,'Pithampur-Mhow', NULL);
-- INSERT INTO centre(id, name, area) VALUES (8,'Hatod-Mhow', NULL);
-- INSERT INTO centre(id, name, area) VALUES (9,'Mandsaur', NULL);
-- INSERT INTO centre(id, name, area) VALUES (10,'Nagpur', NULL);
-- INSERT INTO centre(id, name, area) VALUES (11,'Raipur', NULL);
-- INSERT INTO centre(id, name, area) VALUES (12,'Ahmedabad', NULL);
-- INSERT INTO centre(id, name, area) VALUES (13,'Delhi', NULL);
-- INSERT INTO centre(id, name, area) VALUES (14,'Mumbai', NULL);
-- INSERT INTO centre(id, name, area) VALUES (15,'Biaora', NULL);
-- INSERT INTO centre(id, name, area) VALUES (16,'Potlod', NULL);
-- INSERT INTO centre(id, name, area) VALUES (17,'Kanwan-Mhow', NULL);
-- INSERT INTO centre(id, name, area) VALUES (18,'Sanawad', NULL);
-- INSERT INTO centre(id, name, area) VALUES (19,'Rajgarh', NULL);
-- INSERT INTO centre(id, name, area) VALUES (20,'Pivday', NULL);
-- INSERT INTO centre(id, name, area) VALUES (21,'Ratlam', NULL);
-- INSERT INTO centre(id, name, area) VALUES (22,'Gautampura', NULL);


-- sample data for stockitem
--
--INSERT INTO stockitem (id, name, letternumber, size, cost, category, department) VALUES (3,'Almirah',2007,20,1000,2,3);
--INSERT INTO stockitem (id, name, letternumber, size, cost, category, department) VALUES (4,'Table',2008,20,1000,2,1);
--INSERT INTO stockitem (id, name, letternumber, size, cost, category, department) VALUES (5,'Chair',2009,20,1000,3,4);
--INSERT INTO stockitem (id, name, letternumber, size, cost, category, department) VALUES (6,'Almirah',2009,10,2000,2,1);
--INSERT INTO stockitem (id, name, letternumber, size, cost, category, department) VALUES (7,'Scale',2007,10,2000,1,4);
--INSERT INTO stockitem (id, name, letternumber, size, cost, category, department) VALUES (8,'Chair',2007,20,1000,3,2);

--create table stockuser1 ( USERID VARCHAR(255) NOT NULL PRIMARY KEY, PASSWORD VARCHAR(255), NAME VARCHAR(255));

commit;
