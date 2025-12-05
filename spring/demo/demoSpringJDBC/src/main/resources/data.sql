DROP TABLE IF EXISTS pizza;

CREATE TABLE pizza(
      id int NOT NULL PRIMARY KEY,
      nom NVARCHAR(100),
      prix DECIMAL(5,2)
);

INSERT INTO pizza (id, nom, prix)
VALUES (1, 'CALZONE', 13.60 );
INSERT INTO pizza (id, nom, prix)
VALUES (2, 'DELLA NONA', 14.90 );
INSERT INTO pizza (id, nom, prix)
VALUES (3, 'VULCANO', 14.10 );