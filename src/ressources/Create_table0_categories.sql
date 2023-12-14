-- Create table categories

CREATE TABLE IF NOT EXISTS  categories (
       categoriesId VARCHAR(200) PRIMARY KEY NOT NULL ,
       name varchar(200),
       type VARCHAR(10) NOT NULL CHECK (type IN ('EXPENSE','INCOME'))

);

--- Insert into table categories
 INSERT INTO categories (categoriesId, name, type)
 SELECT 'CAT1', 'Food and drink', 'EXPENSE'
 WHERE NOT EXISTS (
     SELECT 1 FROM categories
     WHERE categoriesId = 'CAT1'
 )
 LIMIT 1;

 INSERT INTO categories (categoriesId, name, type)
 SELECT 'CAT2', 'Buy and store online', 'EXPENSE'
 WHERE NOT EXISTS (
     SELECT 1 FROM categories
     WHERE categoriesId = 'CAT2'
 )
 LIMIT 1;

 INSERT INTO categories (categoriesId, name, type)
 SELECT 'CAT3', 'Housing', 'EXPENSE'
 WHERE NOT EXISTS (
     SELECT 1 FROM categories
     WHERE categoriesId = 'CAT3'
 )
 LIMIT 1;

 INSERT INTO categories (categoriesId, name, type)
  SELECT 'CAT4', 'Transport', 'EXPENSE'
  WHERE NOT EXISTS (
      SELECT 1 FROM categories
      WHERE categoriesId = 'CAT4'
  )
  LIMIT 1;

  INSERT INTO categories (categoriesId, name, type)
   SELECT 'CAT5', 'Vehicle', 'EXPENSE'
   WHERE NOT EXISTS (
       SELECT 1 FROM categories
       WHERE categoriesId = 'CAT5'
   )
   LIMIT 1;

   INSERT INTO categories (categoriesId, name, type)
    SELECT 'CAT6', 'Leisure', 'EXPENSE'
    WHERE NOT EXISTS (
        SELECT 1 FROM categories
        WHERE categoriesId = 'CAT6'
    )
    LIMIT 1;

    INSERT INTO categories (categoriesId, name, type)
     SELECT 'CAT7', 'Multimedia, IT', 'EXPENSE'
     WHERE NOT EXISTS (
         SELECT 1 FROM categories
         WHERE categoriesId = 'CAT7'
     )
     LIMIT 1;

     INSERT INTO categories (categoriesId, name, type)
      SELECT 'CAT8', 'Financial expenses', 'EXPENSE'
      WHERE NOT EXISTS (
          SELECT 1 FROM categories
          WHERE categoriesId = 'CAT8'
      )
      LIMIT 1;

      INSERT INTO categories (categoriesId, name, type)
       SELECT 'CAT9', 'Investment', 'EXPENSE'
       WHERE NOT EXISTS (
           SELECT 1 FROM categories
           WHERE categoriesId = 'CAT9'
       )
       LIMIT 1;

       INSERT INTO categories (categoriesId, name, type)
        SELECT 'CAT10', 'Revenue', 'INCOME'
        WHERE NOT EXISTS (
            SELECT 1 FROM categories
            WHERE categoriesId = 'CAT10'
        )
        LIMIT 1;
