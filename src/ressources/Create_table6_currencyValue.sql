---create table currency value :

CREATE TABLE IF NOT EXISTS CurrencyValue (
    currencyValueId VARCHAR(200) primary key not null ,
    sourceDeviceId VARCHAR(200) REFERENCES currency(currencyId) not null,
    deviceDestination VARCHAR(200) currency(currencyId) not null ,
    amount NUMERIC,
    date TIMESTAMP
);

---insert into currency
INSERT INTO CurrencyValue (currencyValueId, sourceDeviceId, deviceDestination, amount, date)
VALUES
    ('1', 'EUR', 'AR', 100.50, '2023-12-08 12:00:00'),
    ('2', 'AR', 'EUR', 75.25, '2023-12-08 13:00:00');

