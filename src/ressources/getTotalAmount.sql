--create function total amount

CREATE OR REPLACE FUNCTION getTotalAmount(
    in_accountId VARCHAR,
    in_startDate TIMESTAMP,
    in_endDate TIMESTAMP
)
RETURNS TABLE (TotalAmountCredit NUMERIC, TotalAmountDebit NUMERIC) AS $$
BEGIN
    SELECT COALESCE(SUM(CASE WHEN type = 'CREDIT' THEN amount ELSE 0 END), 0) AS TotalAmountCredit,
           COALESCE(SUM(CASE WHEN type = 'DEBIT' THEN amount ELSE 0 END), 0) AS TotalAmountDebit
    INTO TotalAmountCredit, TotalAmountDebit
    FROM transaction
    WHERE accountId = in_accountId
    AND date >= in_startDate
    AND date <= in_endDate;

    RETURN QUERY SELECT TotalAmountCredit, TotalAmountDebit;

END;
$$ LANGUAGE plpgsql;

SELECT * FROM getTotalAmount('ACC003', '2023-12-06 12:30:00'::TIMESTAMP, '2024-01-08 12:00:00'::TIMESTAMP);