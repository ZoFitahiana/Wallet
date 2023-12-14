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
INSERT INTO transaction  (transactionId, amount, label, type, date,accountId,categoriesId )
VALUES ('TXN001', 100.50, 'Groceries', 'DEBIT', '2023-12-06 12:30:00','ACC001','INCOME')
ON CONFLICT (transactionId) DO NOTHING;


INSERT INTO transaction (transactionId, amount, label, type, date,accountId,categoriesId )
VALUES ('TXN002', 50.25, 'Dinner', 'DEBIT', '2023-12-06 18:45:00','ACC002','INCOME')
ON CONFLICT (transactionId) DO NOTHING;


INSERT INTO transaction (transactionId, amount, label, type, date,accountId,categoriesId )
VALUES ('TXN003', 200.00, 'Salary', 'CREDIT', '2023-12-07 09:00:00','ACC003','EXPENSE')
ON CONFLICT (transactionId) DO NOTHING;
