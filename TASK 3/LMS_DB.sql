CREATE DATABASE library_db;
USE library_db;

-- Create Books Table
CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    genre VARCHAR(100),
    status ENUM('available', 'borrowed') DEFAULT 'available',
    INDEX idx_status (status) -- Index for faster queries on 'status'
);

-- Create Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) UNIQUE NOT NULL
);

-- Create Transactions Table with Foreign Key Constraints for Cascading Deletes and Updates
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    borrow_date DATE NOT NULL, -- Remove default here
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert Data into Users Table
-- NOTE: Replace plain text passwords with hashed values for production environments
INSERT INTO users (name, password) VALUES
('admin', '12345678'),
('manthan', 'password');

-- Query Data
SELECT * FROM books;
SELECT * FROM users;
SELECT * FROM transactions;

-- Drop Tables if they exist
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;
