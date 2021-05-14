package com.fernando.expensecontrol.service.exceptions;

public class ValidationError {

    private String campo;
    private String message;

    public ValidationError(String campo, String message) {
        this.campo = campo;
        this.message = message;
    }

    public String getCampo() {
        return campo;
    }

    public String getMessage() {
        return message;
    }
}
