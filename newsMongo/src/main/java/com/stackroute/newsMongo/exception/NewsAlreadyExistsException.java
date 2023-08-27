package com.stackroute.newsMongo.exception;

public class NewsAlreadyExistsException extends Exception {
    public NewsAlreadyExistsException(String message) {
        super(message);
    }
}
