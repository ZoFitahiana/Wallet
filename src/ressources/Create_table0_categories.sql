-- Create table categories

CREATE TABLE IF NOT EXISTS  categories (
       categoriesId VARCHAR(200) PRIMARY KEY NOT NULL ,
       name varchar(200),
       type VARCHAR(10) NOT NULL CHECK (type IN ('EXPENSE','INCOME'))

);

-- insert into table categories

INSERT INTO categories (categoriesId,name,type) VALUES ('CAT1','restaurant','EXPENSE')
ON CONFLICT (categoriesId) DO NOTHING;

INSERT INTO categories (categoriesId,name,type) VALUES ('CAT2','Téléphone et Multimédia','EXPENSE')
ON CONFLICT (categoriesId) DO NOTHING;

INSERT INTO categories (categoriesId,name,type) VALUES ('CAT3','salary','INCOME')
ON CONFLICT (categoriesId) DO NOTHING;