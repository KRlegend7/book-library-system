-- Book Library Management System Database Schema
-- PostgreSQL Database Initialization Script

-- Create database if it doesn't exist
-- Note: This command should be run as a superuser
-- CREATE DATABASE book_library;

-- Connect to the book_library database
-- \c book_library;

-- Create books table
CREATE TABLE IF NOT EXISTS books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publication_year INTEGER NOT NULL CHECK (publication_year >= 1000 AND publication_year <= 2030),
    isbn VARCHAR(17) NOT NULL UNIQUE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_books_title ON books(title);
CREATE INDEX IF NOT EXISTS idx_books_author ON books(author);
CREATE INDEX IF NOT EXISTS idx_books_isbn ON books(isbn);
CREATE INDEX IF NOT EXISTS idx_books_publication_year ON books(publication_year);

-- Create a function to update the updated_at timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Create trigger to automatically update updated_at
DROP TRIGGER IF EXISTS update_books_updated_at ON books;
CREATE TRIGGER update_books_updated_at
    BEFORE UPDATE ON books
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Insert sample data
INSERT INTO books (title, author, publication_year, isbn) VALUES
    ('The Great Gatsby', 'F. Scott Fitzgerald', 1925, '978-0-7432-7356-5'),
    ('To Kill a Mockingbird', 'Harper Lee', 1960, '978-0-06-112008-4'),
    ('1984', 'George Orwell', 1949, '978-0-452-28423-4'),
    ('Pride and Prejudice', 'Jane Austen', 1813, '978-0-14-143951-8'),
    ('The Catcher in the Rye', 'J.D. Salinger', 1951, '978-0-316-76948-0'),
    ('Lord of the Flies', 'William Golding', 1954, '978-0-571-05686-2'),
    ('Animal Farm', 'George Orwell', 1945, '978-0-452-28424-1'),
    ('Brave New World', 'Aldous Huxley', 1932, '978-0-06-085052-4'),
    ('The Lord of the Rings', 'J.R.R. Tolkien', 1954, '978-0-547-92822-7'),
    ('Harry Potter and the Philosopher''s Stone', 'J.K. Rowling', 1997, '978-0-7475-3269-9')
ON CONFLICT (isbn) DO NOTHING;

-- Display initial data
SELECT COUNT(*) as total_books FROM books;
SELECT * FROM books ORDER BY title LIMIT 5;
