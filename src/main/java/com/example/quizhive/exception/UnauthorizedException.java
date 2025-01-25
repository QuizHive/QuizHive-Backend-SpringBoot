package com.example.quizhive.exception;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(401, true, message);
    }
}