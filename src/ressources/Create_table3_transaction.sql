-- create table transaction
CREATE TABLE IF NOT EXISTS transaction (
    transactionId VARCHAR(255) PRIMARY KEY,
    amount NUMERIC NOT NULL,
    label VARCHAR(255) NOT NULL,
    categoriesId VARCHAR(200) REFERENCES categories(categoriesId),
    date TIMESTAMP NOT NULL ,
    accountId VARCHAR(255) REFERENCES account(accountId)
);


-- Insert  transaction
INSERT INTO transaction  (transactionId, amount, label, type, date,accountId)
VALUES ('TXN001', 100.50, 'Groceries', 'CAT1', '2023-12-06 12:30:00','ACC001')
ON CONFLICT (transactionId) DO NOTHING;


INSERT INTO transaction (transactionId, amount, label, type, date,accountId)
VALUES ('TXN002', 50.25, 'Dinner', 'CAT2', '2023-12-06 18:45:00','ACC002')
ON CONFLICT (transactionId) DO NOTHING;


INSERT INTO transaction (transactionId, amount, label, type, date,accountId)
VALUES ('TXN003', 200.00, 'Salary', 'CAT3', '2023-12-07 09:00:00','ACC003')
ON CONFLICT (transactionId) DO NOTHING;
