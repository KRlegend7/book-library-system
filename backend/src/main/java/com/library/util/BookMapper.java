package com.library.util;

import com.library.dto.BookDTO;
import com.library.entity.Book;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for mapping between Entity and DTO objects
 */
public class BookMapper {
    
    private BookMapper() {
        // Utility class - prevent instantiation
    }
    
    /**
     * Convert Book entity to BookDTO
     */
    public static BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        
        return new BookDTO(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getPublicationYear(),
            book.getIsbn(),
            book.getCreatedAt(),
            book.getUpdatedAt()
        );
    }
    
    /**
     * Convert BookDTO to Book entity
     */
    public static Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }
        
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setIsbn(bookDTO.getIsbn());
        book.setCreatedAt(bookDTO.getCreatedAt());
        book.setUpdatedAt(bookDTO.getUpdatedAt());
        
        return book;
    }
    
    /**
     * Convert list of Book entities to list of BookDTOs
     */
    public static List<BookDTO> toDTOList(List<Book> books) {
        if (books == null) {
            return null;
        }
        
        return books.stream()
                   .map(BookMapper::toDTO)
                   .collect(Collectors.toList());
    }
    
    /**
     * Update existing Book entity with data from BookDTO
     */
    public static void updateEntityFromDTO(Book book, BookDTO bookDTO) {
        if (book == null || bookDTO == null) {
            return;
        }
        
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setIsbn(bookDTO.getIsbn());
    }
}
