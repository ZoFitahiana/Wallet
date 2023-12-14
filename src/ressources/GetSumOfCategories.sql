CREATE FUNCTION GetSumOfCategories(
    account_id VARCHAR(200),
    start_date TIMESTAMP,
    end_date TIMESTAMP
)
RETURNS TABLE (category_name VARCHAR(200), total_amount DECIMAL(10, 2))
BEGIN
    DECLARE category_amounts TABLE (category_name VARCHAR(200), total_amount DECIMAL(10, 2));

    INSERT INTO category_amounts (category_name, total_amount)
    SELECT
        c.name,
        COALESCE(SUM(t.amount), 0) AS total_amount
    FROM categories c
    LEFT JOIN transactions t ON c.categoriesId = t.categoryId
        AND t.accountId = account_id
        AND t.dateAndTime >= start_date
        AND t.dateAndTime <= end_date
    GROUP BY c.name;

    RETURN category_amounts;
END;
