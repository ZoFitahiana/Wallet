-- create table for history balance of account :

CREATE TABLE IF NOT EXISTS history  (
    historyId serial primary key  not null ,
    transactionId varchar(200) not null ,
    accountId varchar(200) not null ,
    balance numeric
);