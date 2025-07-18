package com.library.service;

import com.library.dto.BookDTO;
import com.library.entity.Book;
import com.library.exception.BookNotFoundException;
import com.library.exception.DuplicateIsbnException;
import com.library.util.BookMapper;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Stateless EJB for Book business logic operations
 */
@Stateless
public class BookService {
    
    private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());
    
    @PersistenceContext(unitName = "bookLibraryPU")
    private EntityManager entityManager;
    
    @Inject
    private Validator validator;
    
    /**
     * Create a new book
     */
    public BookDTO createBook(BookDTO bookDTO) {
        LOGGER.info("Creating new book: " + bookDTO.getTitle());
        
        // Validate input
        validateBookDTO(bookDTO);
        
        // Check for duplicate ISBN
        if (isIsbnExists(bookDTO.getIsbn())) {
            throw new DuplicateIsbnException(bookDTO.getIsbn());
        }
        
        try {
            Book book = BookMapper.toEntity(bookDTO);
            entityManager.persist(book);
            entityManager.flush(); // Force immediate persistence to get generated ID
            
            LOGGER.info("Successfully created book with ID: " + book.getId());
            return BookMapper.toDTO(book);
            
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "Error creating book: " + e.getMessage(), e);
            throw new RuntimeException("Failed to create book: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get all books
     */
    public List<BookDTO> getAllBooks() {
        LOGGER.info("Fetching all books");
        
        try {
            List<Book> books = entityManager.createNamedQuery("Book.findAll", Book.class)
                                          .getResultList();
            
            LOGGER.info("Found " + books.size() + " books");
            return BookMapper.toDTOList(books);
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching all books: " + e.getMessage(), e);
            throw new RuntimeException("Failed to fetch books: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get book by ID
     */
    public BookDTO getBookById(Long id) {
        LOGGER.info("Fetching book with ID: " + id);
        
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        
        Book book = entityManager.find(Book.class, id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        
        LOGGER.info("Successfully found book: " + book.getTitle());
        return BookMapper.toDTO(book);
    }
    
    /**
     * Update an existing book
     */
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        LOGGER.info("Updating book with ID: " + id);
        
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        
        // Validate input
        validateBookDTO(bookDTO);
        
        Book existingBook = entityManager.find(Book.class, id);
        if (existingBook == null) {
            throw new BookNotFoundException(id);
        }
        
        // Check for duplicate ISBN (only if ISBN is being changed)
        if (!existingBook.getIsbn().equals(bookDTO.getIsbn()) && isIsbnExists(bookDTO.getIsbn())) {
            throw new DuplicateIsbnException(bookDTO.getIsbn());
        }
        
        try {
            BookMapper.updateEntityFromDTO(existingBook, bookDTO);
            entityManager.merge(existingBook);
            
            LOGGER.info("Successfully updated book with ID: " + id);
            return BookMapper.toDTO(existingBook);
            
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "Error updating book with ID " + id + ": " + e.getMessage(), e);
            throw new RuntimeException("Failed to update book: " + e.getMessage(), e);
        }
    }
    
    /**
     * Delete a book by ID
     */
    public void deleteBook(Long id) {
        LOGGER.info("Deleting book with ID: " + id);
        
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        
        Book book = entityManager.find(Book.class, id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        
        try {
            entityManager.remove(book);
            LOGGER.info("Successfully deleted book with ID: " + id);
            
        } catch (PersistenceException e) {
            LOGGER.log(Level.SEVERE, "Error deleting book with ID " + id + ": " + e.getMessage(), e);
            throw new RuntimeException("Failed to delete book: " + e.getMessage(), e);
        }
    }
    
    /**
     * Search books by author
     */
    public List<BookDTO> searchBooksByAuthor(String author) {
        LOGGER.info("Searching books by author: " + author);
        
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }
        
        try {
            List<Book> books = entityManager.createNamedQuery("Book.findByAuthor", Book.class)
                                          .setParameter("author", "%" + author.trim() + "%")
                                          .getResultList();
            
            LOGGER.info("Found " + books.size() + " books by author: " + author);
            return BookMapper.toDTOList(books);
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error searching books by author: " + e.getMessage(), e);
            throw new RuntimeException("Failed to search books by author: " + e.getMessage(), e);
        }
    }
    
    /**
     * Check if ISBN already exists
     */
    private boolean isIsbnExists(String isbn) {
        try {
            entityManager.createNamedQuery("Book.findByIsbn", Book.class)
                         .setParameter("isbn", isbn)
                         .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
    
    /**
     * Validate BookDTO using Bean Validation
     */
    private void validateBookDTO(BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new IllegalArgumentException("Book data cannot be null");
        }
        
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(bookDTO);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<BookDTO> violation : violations) {
                sb.append(violation.getMessage()).append("; ");
            }
            throw new ConstraintViolationException("Validation failed: " + sb.toString(), violations);
        }
    }
}
