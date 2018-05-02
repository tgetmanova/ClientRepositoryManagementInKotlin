CREATE SCHEMA myschema;

USE myschema;

CREATE TABLE Clients (
  ClientID    INT         NOT NULL AUTO_INCREMENT,
  FirstName   VARCHAR(40) NOT NULL,
  LastName    VARCHAR(40) NOT NULL,
  MiddleName  VARCHAR(40),
  DateOfBirth DATETIME,
  PRIMARY KEY (ClientID)
);

CALL fillClients(10);
SELECT *
FROM Clients;

CREATE TABLE Countries (
  Name VARCHAR(25) UNIQUE,
  Code VARCHAR(2) UNIQUE
);

INSERT INTO Countries VALUES ('United States', 'US'), ('Russian Federation', 'RU');

SELECT *
FROM Countries;


CREATE TABLE Contacts (
  ContactID  INT NOT NULL AUTO_INCREMENT,
  ClientID   INT,
  PhoneNumber VARCHAR(20),
  Address    VARCHAR(100),
  Email      VARCHAR(40),
  PRIMARY KEY (ContactID),
  FOREIGN KEY (ClientID) REFERENCES Clients (ClientID)
);

INSERT INTO Contacts (ClientID, PhoneNumber, Address, Email)
VALUES (12, '+923123', 'fsdfsdfsadf', 'adsffdh@sadsads.test' );

SELECT *
FROM Clients;

SELECT * FROM Contacts;