package com.stackroute.newsMongo.exception;

public class NewsNotFoundException extends Exception{
    public NewsNotFoundException(String message) {
        super(message);
    }
}
