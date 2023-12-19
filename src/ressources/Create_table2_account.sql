CREATE TABLE IF NOT EXISTS account (
    accountId VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    balance NUMERIC,
    lastUpdate TIMESTAMP,
    currencyId VARCHAR(255) REFERENCES currency(currencyId),
    type VARCHAR(50) CHECK (type IN ('Bank', 'Cash', 'Mobile Money'))
);

-- Insert data into account table
INSERT INTO account (accountId, name, balance, lastUpdate, currencyId, type)
SELECT 'ACC001', 'John Doe', 100.25, '2023-12-06 12:30:00', 'EUR', 'Bank'
WHERE NOT EXISTS (
    SELECT 1 FROM account
    WHERE accountId = 'ACC001'
)
LIMIT 1;

INSERT INTO account (accountId, name, balance, lastUpdate, currencyId, type)
SELECT 'ACC002', 'Jane Smith', 120.75, '2023-12-06 12:40:00', 'EUR', 'Cash'
WHERE NOT EXISTS (
    SELECT 1 FROM account
    WHERE accountId = 'ACC002'
)
LIMIT 1;

INSERT INTO account (accountId, name, balance, lastUpdate, currencyId, type)
SELECT 'ACC003', 'Alice Johnson', 500.00, '2023-12-06 13:30:00', 'AR', 'Mobile Money'
WHERE NOT EXISTS (
    SELECT 1 FROM account
    WHERE accountId = 'ACC003'
)
LIMIT 1;
