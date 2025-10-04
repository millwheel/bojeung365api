package com.example.bojeung365api.exception.custom;

public class InvalidAuthorityException extends RuntimeException {
    public InvalidAuthorityException() {
        super("Invalid authority to access this resource.");
    }
}
