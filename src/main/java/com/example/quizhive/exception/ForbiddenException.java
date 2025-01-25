package com.example.quizhive.exception;

public class ForbiddenException extends ApiException {
    public ForbiddenException(String message) {
        super(403, true, message);
    }
}