package com.example.quizhive.exception;

public class ApiException extends RuntimeException {
    private final int status;
    private final boolean isOperational;

    public ApiException(int status, boolean isOperational, String message) {
        super(message);
        this.status = status;
        this.isOperational = isOperational;
    }

    public int getStatus() {
        return status;
    }

    public boolean isOperational() {
        return isOperational;
    }
}