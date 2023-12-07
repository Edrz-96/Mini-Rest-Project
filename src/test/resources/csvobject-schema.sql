DROP TABLE IF EXISTS Customer cascade;
CREATE TABLE Customer
(
   customerRef BIGINT PRIMARY KEY AUTO_INCREMENT,
   customerName VARCHAR (255) NOT NULL,
   addressLineOne VARCHAR (255),
   addressLineTwo VARCHAR (255),
   town VARCHAR (255),
   county VARCHAR (255),
   country VARCHAR (255),
   postcode VARCHAR (20)
);