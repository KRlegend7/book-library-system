package com.library.dto;

/**
 * Response wrapper for API responses
 */
public class ApiResponse<T> {
    
    private boolean success;
    private String message;
    private T data;
    private String error;
    
    // Private constructor to enforce factory methods
    private ApiResponse() {}
    
    // Success response with data
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        response.message = "Operation completed successfully";
        return response;
    }
    
    // Success response with data and custom message
    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        response.message = message;
        return response;
    }
    
    // Success response with just message
    public static <T> ApiResponse<T> success(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.message = message;
        return response;
    }
    
    // Error response
    public static <T> ApiResponse<T> error(String error) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.error = error;
        response.message = "Operation failed";
        return response;
    }
    
    // Error response with custom message
    public static <T> ApiResponse<T> error(String message, String error) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.message = message;
        response.error = error;
        return response;
    }
    
    // Getters
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public T getData() {
        return data;
    }
    
    public String getError() {
        return error;
    }
    
    // Setters (for JSON serialization)
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public void setError(String error) {
        this.error = error;
    }
}
