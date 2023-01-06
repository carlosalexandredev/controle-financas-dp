package com.example.demo.fortune.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Integer id) {
        super(String.format("Resource not found. Id %d", id));
    }

    public ResourceNotFoundException(Long id) {
        super(String.format("Resource not found. Id %d", id));
    }

    public ResourceNotFoundException(String email) {
        super(String.format("Resource not found. Email %s", email));
    }
}