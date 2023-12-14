CREATE FUNCTION GetSumOfMoneyTransactions(
    account_id INT,
    start_date DATETIME,
    end_date DATETIME
)
RETURNS DECIMAL(10, 2)
BEGIN
    DECLARE total_amount DECIMAL(10, 2);

    SELECT
        SUM(CASE WHEN Type = 'Crédit' THEN Amount ELSE 0 END) -
        SUM(CASE WHEN Type = 'Débit' THEN Amount ELSE 0 END)
    INTO total_amount
    FROM Transactions
    WHERE AccountID = account_id
        AND DateAndTime >= start_date
        AND DateAndTime <= end_date;

    RETURN total_amount;
END;
