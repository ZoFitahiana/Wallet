--create function get total amount by  categories


CREATE OR REPLACE FUNCTION getCategoryAmounts(
    in_accountId VARCHAR,
    in_startDate TIMESTAMP,
    in_endDate TIMESTAMP
)
RETURNS TABLE (category_name VARCHAR, total_amount NUMERIC) AS $$
BEGIN
    RETURN QUERY
    SELECT categories.name AS category_name,
           COALESCE(SUM(CASE WHEN transaction.amount IS NOT NULL THEN transaction.amount ELSE 0 END), 0) AS total_amount
    FROM categories
    LEFT JOIN transaction ON categories.categoriesId = transaction.categoriesId
    LEFT JOIN account ON account.accountId = transaction.accountId
    WHERE account.accountId = in_accountId
    AND transaction.date BETWEEN in_startDate AND in_endDate
    GROUP BY categories.name;

END;
$$ LANGUAGE plpgsql;
SELECT * FROM getCategoryAmounts('ACC003', '2023-12-06 12:30:00'::TIMESTAMP, '2024-01-08 12:00:00'::TIMESTAMP);
