CREATE FUNCTION GetRandomString(length INT)
  RETURNS VARCHAR(150)
DETERMINISTIC
  BEGIN
    DECLARE randomString VARCHAR(150) DEFAULT '';
    DECLARE source VARCHAR(150);

    SET @i = 0;
    SET source = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvqxyz,./?\'|][{}()-_=+*&^:;#@$%0123456789';

    WHILE @i < length DO
      SET randomString = concat(randomString, substring(source, rand() * char_length(source), 1));
      SET @i = @i + 1;
    END WHILE;

    RETURN (randomString);
  END;