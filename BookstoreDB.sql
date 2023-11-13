CREATE TABLE Authors(
	author_id BIGINT PRIMARY KEY,
	name VARCHAR(50),
	birthdate DATE
);

CREATE TABLE Books(
	book_id BIGINT PRIMARY KEY,
	author_id BIGINT REFERENCES Authors(author_id) ON DELETE CASCADE,
	title VARCHAR(50),
	price DECIMAL,
	publish_date DATE
);
 
CREATE TABLE Customers(
	customer_id BIGINT PRIMARY KEY,
	name VARCHAR(50),
	email VARCHAR(50),
	join_date DATE
);

INSERT INTO Authors VALUES (1, 'john doe', '10-10-1930');
INSERT INTO Authors VALUES (2, 'jane doe', '10-10-1944');
INSERT INTO Authors VALUES (3, 'josh doe', '10-10-1950');
INSERT INTO Authors VALUES (4, 'johnny doe', '10-10-1994');
INSERT INTO Authors VALUES (5, 'Janie Doe', '10-10-1983');

INSERT INTO Books VALUES (1, 1, 'Pride and prejudice', 200, '9-9-2020');
INSERT INTO Books VALUES (2, 3, 'Anne with an E', 230, '1-3-2021');
INSERT INTO Books VALUES (3, 5, 'Friends, Love, and the big terrible thing', 500, '3-7-2013');
INSERT INTO Books VALUES (4, 4, 'The Big Four', 100, '6-8-1987');
INSERT INTO Books VALUES (5, 2, 'Principles', 600, '7-10-2023');

INSERT INTO Customers VALUES (1, 'john doe', 'john@gmail.com', '1-1-2012');
INSERT INTO Customers VALUES (2, 'jane doe', 'jane@gmail.com', '2-1-2004');
INSERT INTO Customers VALUES (3, 'josh doe', 'josh@gmail.com', '5-7-2001');
INSERT INTO Customers VALUES (4, 'johnny doe', 'johnny@gmail.com', '12-12-2012');
INSERT INTO Customers VALUES (5, 'Janie Doe', 'janie@gmail.com', '8-10-2015');

--Update Data
UPDATE Books 
SET price = price * 1.1;

--Delete Data
DELETE FROM Authors
WHERE EXTRACT(YEAR FROM Authors.birthdate) < 1950

--Basic Queries
SELECT * FROM Books 
WHERE EXTRACT(YEAR FROM publish_date) > 2010;

--Joins
SELECT Books.title,
Authors.name FROM Books 
INNER join Authors ON Books.author_id = Authors.author_id;

--Aggregate Functions
SELECT AVG(price) 
as average_price
FROM books;

--Views
CREATE VIEW RecentBooks AS
SELECT * FROM Books 
WHERE EXTRACT(YEAR FROM publish_date) > 2018;


--Index
CREATE INDEX idx_publish_date ON Books(publish_date);

--Procedure
CREATE OR REPLACE FUNCTION calculate_total_sales_per_author()
RETURNS TABLE (author_id BIGINT, author_name VARCHAR(50), total_sales DECIMAL) AS $$
BEGIN
    RETURN QUERY
    SELECT Authors.author_id, Authors.name, SUM(Books.price) as total_sales
    FROM Authors
    LEFT JOIN Books ON Authors.author_id = Books.author_id
    GROUP BY Authors.author_id, Authors.name;
END;
$$ LANGUAGE plpgsql;

--Calling procedure
SELECT * FROM calculate_total_sales_per_author();

-- Create an audit table to store the changes
CREATE TABLE Books_Audit (
    audit_id serial PRIMARY KEY,
    book_id integer,
    action_type text,
    changed_at timestamptz DEFAULT NOW(),
    changed_by text
);

--Create a trigger to log changes in the Books table
CREATE OR REPLACE FUNCTION log_books_changes()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'DELETE' THEN
        INSERT INTO Books_Audit (book_id, action_type, changed_by)
        VALUES (OLD.book_id, 'DELETE', current_user);
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO Books_Audit (book_id, action_type, changed_by)
        VALUES (NEW.book_id, 'UPDATE', current_user);
    ELSIF TG_OP = 'INSERT' THEN
        INSERT INTO Books_Audit (book_id, action_type, changed_by)
        VALUES (NEW.book_id, 'INSERT', current_user);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger on the Books table to call the trigger function
CREATE TRIGGER books_audit_trigger
AFTER INSERT OR UPDATE OR DELETE ON Books
FOR EACH ROW
EXECUTE FUNCTION log_books_changes();

INSERT INTO Books VALUES (6, 5, 'The shining', 900, '7-10-1995');
INSERT INTO Books VALUES (7, 5, 'Little Women', 900, '2-9-1999');
INSERT INTO Books VALUES (8, 5, 'Java for dummies', 900, '8-1-2009');
INSERT INTO Books VALUES (9, 5, 'vogue', 900, '6-10-1995');

DELETE FROM Books WHERE title = 'vogue'

UPDATE Books
SET PUBLISH_DATE = '1-1-1868'
WHERE title = 'Little Women';

SELECT * FROM Books_Audit;
