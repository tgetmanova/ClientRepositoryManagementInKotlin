CREATE PROCEDURE fillClients(numberOfClients INT)
  BEGIN

    DECLARE j INT DEFAULT 0;

    WHILE j < numberOfClients DO
      INSERT INTO Clients (FirstName, LastName, MiddleName, DateOfBirth)
      VALUES (GetRandomString(10), GetRandomString(10), NULL, NULL);
      SET j = j + 1;
    END WHILE;

  END;