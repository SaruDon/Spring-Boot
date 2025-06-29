package com.example.department.exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message) {
        super(message);
    }
}

