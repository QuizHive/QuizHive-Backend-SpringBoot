package com.example.quizhive.exception;

public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(400, true, message);
    }
}