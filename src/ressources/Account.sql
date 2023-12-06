CREATE TABLE IF NOT EXISTS account (
    accountId VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    balance NUMERIC,
    lastUpdate TIMESTAMP,
    currencyId VARCHAR(255) REFERENCES currency(currencyId),
    transactionId VARCHAR(255) REFERENCES transaction(transactionId),
    type VARCHAR(50) CHECK (type IN ('Bank', 'Cash', 'Mobile Money'))
);

-- Insert data into account table
INSERT INTO account (accountId, name, balance, lastUpdate, currencyId, transactionId, type)
VALUES
('ACC001', 'John Doe', 350.25, '2023-12-06 12:30:00', 'USD', 'TXN001', 'Bank');

INSERT INTO account (accountId, name, balance, lastUpdate, currencyId, transactionId, type)
VALUES
('ACC002', 'Jane Smith', 120.75, '2023-12-06 12:40:00', 'EUR', 'TXN003', 'Cash');

INSERT INTO account (accountId, name, balance, lastUpdate, currencyId, transactionId, type)
VALUES
('ACC003', 'Alice Johnson', 500.00, '2023-12-06 13:30:00', 'GBP', 'TXN004', 'Mobile Money');
