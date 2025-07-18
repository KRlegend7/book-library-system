package com.library.rest;

import com.library.dto.ApiResponse;
import com.library.dto.BookDTO;
import com.library.exception.BookNotFoundException;
import com.library.exception.DuplicateIsbnException;
import com.library.service.BookService;
import jakarta.ejb.EJB;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * REST Controller for Book operations
 */
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {
    
    private static final Logger LOGGER = Logger.getLogger(BookController.class.getName());
    
    @EJB
    private BookService bookService;
    
    /**
     * Create a new book
     * POST /api/books
     */
    @POST
    public Response createBook(BookDTO bookDTO) {
        try {
            LOGGER.info("REST: Creating new book");
            BookDTO createdBook = bookService.createBook(bookDTO);
            
            return Response.status(Response.Status.CREATED)
                          .entity(ApiResponse.success(createdBook, "Book created successfully"))
                          .build();
                          
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.WARNING, "Validation error creating book: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                          .entity(ApiResponse.error("Validation failed", e.getMessage()))
                          .build();
                          
        } catch (DuplicateIsbnException e) {
            LOGGER.log(Level.WARNING, "Duplicate ISBN error: " + e.getMessage());
            return Response.status(Response.Status.CONFLICT)
                          .entity(ApiResponse.error("Duplicate ISBN", e.getMessage()))
                          .build();
                          
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Invalid argument: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                          .entity(ApiResponse.error("Invalid request", e.getMessage()))
                          .build();
                          
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error creating book: " + e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity(ApiResponse.error("Internal server error", "Failed to create book"))
                          .build();
        }
    }
    
    /**
     * Get all books
     * GET /api/books
     */
    @GET
    public Response getAllBooks() {
        try {
            LOGGER.info("REST: Fetching all books");
            List<BookDTO> books = bookService.getAllBooks();
            
            return Response.ok(ApiResponse.success(books, "Books retrieved successfully"))
                          .build();
                          
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching all books: " + e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity(ApiResponse.error("Internal server error", "Failed to fetch books"))
                          .build();
        }
    }
    
    /**
     * Get book by ID
     * GET /api/books/{id}
     */
    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Long id) {
        try {
            LOGGER.info("REST: Fetching book with ID: " + id);
            BookDTO book = bookService.getBookById(id);
            
            return Response.ok(ApiResponse.success(book, "Book retrieved successfully"))
                          .build();
                          
        } catch (BookNotFoundException e) {
            LOGGER.log(Level.WARNING, "Book not found: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                          .entity(ApiResponse.error("Book not found", e.getMessage()))
                          .build();
                          
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Invalid book ID: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                          .entity(ApiResponse.error("Invalid request", e.getMessage()))
                          .build();
                          
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching book with ID " + id + ": " + e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity(ApiResponse.error("Internal server error", "Failed to fetch book"))
                          .build();
        }
    }
    
    /**
     * Update an existing book
     * PUT /api/books/{id}
     */
    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") Long id, BookDTO bookDTO) {
        try {
            LOGGER.info("REST: Updating book with ID: " + id);
            BookDTO updatedBook = bookService.updateBook(id, bookDTO);
            
            return Response.ok(ApiResponse.success(updatedBook, "Book updated successfully"))
                          .build();
                          
        } catch (BookNotFoundException e) {
            LOGGER.log(Level.WARNING, "Book not found for update: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                          .entity(ApiResponse.error("Book not found", e.getMessage()))
                          .build();
                          
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.WARNING, "Validation error updating book: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                          .entity(ApiResponse.error("Validation failed", e.getMessage()))
                          .build();
                          
        } catch (DuplicateIsbnException e) {
            LOGGER.log(Level.WARNING, "Duplicate ISBN error on update: " + e.getMessage());
            return Response.status(Response.Status.CONFLICT)
                          .entity(ApiResponse.error("Duplicate ISBN", e.getMessage()))
                          .build();
                          
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Invalid argument for update: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                          .entity(ApiResponse.error("Invalid request", e.getMessage()))
                          .build();
                          
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating book with ID " + id + ": " + e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity(ApiResponse.error("Internal server error", "Failed to update book"))
                          .build();
        }
    }
    
    /**
     * Delete a book
     * DELETE /api/books/{id}
     */
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        try {
            LOGGER.info("REST: Deleting book with ID: " + id);
            bookService.deleteBook(id);
            
            return Response.ok(ApiResponse.success("Book deleted successfully"))
                          .build();
                          
        } catch (BookNotFoundException e) {
            LOGGER.log(Level.WARNING, "Book not found for deletion: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                          .entity(ApiResponse.error("Book not found", e.getMessage()))
                          .build();
                          
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Invalid book ID for deletion: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                          .entity(ApiResponse.error("Invalid request", e.getMessage()))
                          .build();
                          
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting book with ID " + id + ": " + e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity(ApiResponse.error("Internal server error", "Failed to delete book"))
                          .build();
        }
    }
    
    /**
     * Search books by author
     * GET /api/books/search/author?q={author}
     */
    @GET
    @Path("/search/author")
    public Response searchBooksByAuthor(@QueryParam("q") String author) {
        try {
            LOGGER.info("REST: Searching books by author: " + author);
            List<BookDTO> books = bookService.searchBooksByAuthor(author);
            
            return Response.ok(ApiResponse.success(books, "Books search completed"))
                          .build();
                          
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Invalid search parameter: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                          .entity(ApiResponse.error("Invalid request", e.getMessage()))
                          .build();
                          
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error searching books by author: " + e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity(ApiResponse.error("Internal server error", "Failed to search books"))
                          .build();
        }
    }
}
