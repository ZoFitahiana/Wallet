-- create table transaction
CREATE TABLE IF NOT EXISTS transaction (
    transactionId VARCHAR(255) PRIMARY KEY,
    amount NUMERIC,
    description VARCHAR(255),
    accountId VARCHAR(255) REFERENCES account(accountId)
);

-- insert for transaction
INSERT INTO transaction (transactionId, amount, description, accountId)
VALUES ('txn001', 50.00, 'Purchase of goods', '123456')
ON CONFLICT (transactionId) DO NOTHING;

INSERT INTO transaction (transactionId, amount, description, accountId)
VALUES ('txn002', -20.00, 'Refund for returned item', '789012')
ON CONFLICT (transactionId) DO NOTHING;

INSERT INTO transaction (transactionId, amount, description, accountId)
VALUES ('txn003', 100.00, 'Salary deposit', '345678')
ON CONFLICT (transactionId) DO NOTHING;
