package com.example.bojeung365api.exception.custom;

public class DataNotFoundException extends RuntimeException {

    private static final String DATA_NOT_FOUND_MESSAGE = "%s doesn't exist";

    public DataNotFoundException(String entityName) {
        super(String.format(DATA_NOT_FOUND_MESSAGE, entityName));
    }
}
