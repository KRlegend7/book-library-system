package com.library.exception;

/**
 * Custom exception for duplicate ISBN scenarios
 */
public class DuplicateIsbnException extends RuntimeException {
    
    public DuplicateIsbnException(String message) {
        super(message);
    }
    
    public DuplicateIsbnException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DuplicateIsbnException(String isbn) {
        super("Book with ISBN '" + isbn + "' already exists");
    }
}
