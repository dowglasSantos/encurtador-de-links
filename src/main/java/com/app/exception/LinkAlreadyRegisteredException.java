package com.app.exception;

public class LinkAlreadyRegisteredException extends RuntimeException {
    public LinkAlreadyRegisteredException(String message) {
        super(message);
    }
}
