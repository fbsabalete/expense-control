package com.fernando.expensecontrol.service.exceptions;

public class StandardError {

    private int statusCode;
    private String message;

    public StandardError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
