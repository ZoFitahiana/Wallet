-- Création de la séquence
CREATE SEQUENCE transfer_history_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO CYCLE;

-- Création de la table TransferHistory avec utilisation de la séquence
CREATE TABLE IF NOT EXISTS TransferHistory (
    TransferHistoryId INTEGER DEFAULT nextval('transfer_history_seq') PRIMARY KEY NOT NULL,
    creditorTransactionId VARCHAR(200) REFERENCES transaction(transactionID) NOT NULL,
    debtorTransactionId VARCHAR(200) REFERENCES transaction(transactionID) NOT NULL,
    dateOfTransaction TIMESTAMP
);
