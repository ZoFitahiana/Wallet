-- Création de la séquence
CREATE SEQUENCE history_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO CYCLE;

-- Création de la table history avec utilisation de la séquence
CREATE TABLE IF NOT EXISTS history (
    historyId INTEGER DEFAULT nextval('history_seq') PRIMARY KEY NOT NULL,
    transactionId VARCHAR(200) REFERENCES transaction(transactionId) NOT NULL,
    accountId VARCHAR(200) REFERENCES account(accountId) NOT NULL,
    balance NUMERIC
);
