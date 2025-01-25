package com.example.quizhive.exception;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(404, true, message);
    }
}