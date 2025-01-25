package com.example.quizhive.exception;

public class InternalServerErrorException extends ApiException {
    public InternalServerErrorException(String message) {
        super(500, false, message);
    }
}