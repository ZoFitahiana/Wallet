---create table currency value :

CREATE TABLE IF NOT EXISTS CurrencyValue (
    currencyValueId VARCHAR(200) primary key not null ,
    sourceDeviceId VARCHAR(200) REFERENCES currency(currencyId) not null,
    deviceDestination VARCHAR(200) REFERENCES currency(currencyId) not null ,
    amount NUMERIC,
    date TIMESTAMP
);

---insert into currency
INSERT INTO CurrencyValue (currencyValueId, sourceDeviceId, deviceDestination, amount, date)
VALUES
    ('1', 'EUR', 'AR', 4500.00, '2023-12-08 12:00:00'),
    ('2', 'AR', 'EUR', 500.00, '2023-12-08 13:00:00');

