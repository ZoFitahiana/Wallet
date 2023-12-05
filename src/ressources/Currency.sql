--create table currency
CREATE TABLE IF NOT EXISTS currency (
    currencyId VARCHAR(255)  PRIMARY KEY ,
    name VARCHAR(255),
    code VARCHAR(10)
);

-- insert for currency
INSERT INTO currency (currencyId, name, code)
VALUES ('USD', 'US Dollar', 'USD')
ON CONFLICT (currencyId) DO NOTHING;

INSERT INTO currency (currencyId, name, code)
VALUES ('EUR', 'Euro', 'EUR')
ON CONFLICT (currencyId) DO NOTHING;

INSERT INTO currency (currencyId, name, code)
VALUES ('GBP', 'British Pound', 'GBP')
ON CONFLICT (currencyId) DO NOTHING;