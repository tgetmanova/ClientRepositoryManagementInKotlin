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