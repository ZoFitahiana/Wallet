-- create table account
CREATE TABLE IF NOT EXISTS account (
    accountId VARCHAR(255) PRIMARY KEY,
    balance int,
    currencyId VARCHAR(255) REFERENCES currency(currencyId)
);

-- insert for account
INSERT INTO account (accountId, balance, currencyId)
VALUES ('123456', 1000, 'USD')
ON CONFLICT (accountId) DO NOTHING;

INSERT INTO account (accountId, balance, currencyId)
VALUES ('789012', 500, 'EUR')
ON CONFLICT (accountId) DO NOTHING;

INSERT INTO account (accountId, balance, currencyId)
VALUES ('345678', 750, 'GBP')
ON CONFLICT (accountId) DO NOTHING;
