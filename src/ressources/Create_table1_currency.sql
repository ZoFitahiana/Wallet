--create table currency
CREATE TABLE IF NOT EXISTS currency (
    currencyId VARCHAR(255)  PRIMARY KEY ,
    name VARCHAR(255),
    code VARCHAR(10)
);

-- Insert into table currency
INSERT INTO currency (currencyId, name, code)
SELECT 'EUR', 'Euro', 'EUR'
WHERE NOT EXISTS (
    SELECT 1 FROM currency
    WHERE currencyId = 'EUR'
)
LIMIT 1;

INSERT INTO currency (currencyId, name, code)
SELECT 'AR', 'ariary', 'MGA'
WHERE NOT EXISTS (
    SELECT 1 FROM currency
    WHERE currencyId = 'AR'
)
LIMIT 1;

