package com.example.quizhive.exception;

public class ConflictException extends ApiException {
    public ConflictException(String message) {
        super(409, true, message);
    }
}