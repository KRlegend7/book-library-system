package com.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Book entity representing a book in the library system
 */
@Entity
@Table(name = "books")
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b ORDER BY b.title"),
    @NamedQuery(name = "Book.findByIsbn", query = "SELECT b FROM Book b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Book.findByAuthor", query = "SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(:author) ORDER BY b.title")
})
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Title cannot be empty")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    @Column(nullable = false)
    private String title;
    
    @NotBlank(message = "Author cannot be empty")
    @Size(max = 255, message = "Author cannot exceed 255 characters")
    @Column(nullable = false)
    private String author;
    
    @NotNull(message = "Publication year is required")
    @Min(value = 1000, message = "Publication year must be at least 1000")
    @Max(value = 2030, message = "Publication year cannot be in the future")
    @Column(name = "publication_year", nullable = false)
    private Integer publicationYear;
    
    @NotBlank(message = "ISBN cannot be empty")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", 
             message = "Invalid ISBN format")
    @Column(unique = true, nullable = false)
    private String isbn;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Default constructor
    public Book() {}
    
    // Constructor for creating new books
    public Book(String title, String author, Integer publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public Integer getPublicationYear() {
        return publicationYear;
    }
    
    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(isbn, book.isbn);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", isbn='" + isbn + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
