package com.example.vishoov.exceptions;

public class RequestFailureException extends RuntimeException {
    public RequestFailureException(String message) {
        super(message);
    }
}
