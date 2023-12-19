-- create table transaction
CREATE TABLE IF NOT EXISTS transaction (
    transactionId VARCHAR(255) PRIMARY KEY,
    amount NUMERIC NOT NULL,
    label VARCHAR(255) NOT NULL,
    type VARCHAR(10) NOT NULL CHECK (type IN ('DEBIT', 'CREDIT')),
    date TIMESTAMP NOT NULL ,
    accountId VARCHAR(255) REFERENCES account(accountId),
    categoriesId  VARCHAR(10) REFERENCES categories(categoriesId)
);


-- Insert  transaction
-- Insert transaction
INSERT INTO transaction (transactionId, amount, label, type, date, accountId, categoriesId)
SELECT 'TXN001', 100.50, 'Groceries', 'DEBIT', '2023-12-06 12:30:00', 'ACC001', 'CAT1'
WHERE NOT EXISTS (
    SELECT 1 FROM transaction
    WHERE transactionId = 'TXN001'
)
LIMIT 1;

INSERT INTO transaction (transactionId, amount, label, type, date, accountId, categoriesId)
SELECT 'TXN002', 50.25, 'Dinner', 'DEBIT', '2023-12-06 18:45:00', 'ACC002', 'CAT2'
WHERE NOT EXISTS (
    SELECT 1 FROM transaction
    WHERE transactionId = 'TXN002'
)
LIMIT 1;

INSERT INTO transaction (transactionId, amount, label, type, date, accountId, categoriesId)
SELECT 'TXN003', 200.00, 'Salary', 'CREDIT', '2023-12-07 09:00:00', 'ACC003', 'CAT3'
WHERE NOT EXISTS (
    SELECT 1 FROM transaction
    WHERE transactionId = 'TXN003'
)
LIMIT 1;
