package com.fernando.expensecontrol.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public StandardError notFound (ObjectNotFoundException e){
        return new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationError> validationError (MethodArgumentNotValidException e){
        List<ValidationError> errorList = new ArrayList<>();
        e.getFieldErrors().forEach(fieldError -> errorList.add(new ValidationError(fieldError.getField(), fieldError.getDefaultMessage())));
        return errorList;
    }

}
