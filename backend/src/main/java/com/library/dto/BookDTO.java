package com.library.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Book operations
 */
public class BookDTO {
    
    private Long id;
    
    @NotBlank(message = "Title cannot be empty")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;
    
    @NotBlank(message = "Author cannot be empty")
    @Size(max = 255, message = "Author cannot exceed 255 characters")
    private String author;
    
    @NotNull(message = "Publication year is required")
    @Min(value = 1000, message = "Publication year must be at least 1000")
    @Max(value = 2030, message = "Publication year cannot be in the future")
    private Integer publicationYear;
    
    @NotBlank(message = "ISBN cannot be empty")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", 
             message = "Invalid ISBN format")
    private String isbn;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Default constructor
    public BookDTO() {}
    
    // Constructor for creating DTOs
    public BookDTO(String title, String author, Integer publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }
    
    // Full constructor
    public BookDTO(Long id, String title, String author, Integer publicationYear, String isbn, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
}
