package com.cydeo.exception;

import com.cydeo.dto.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class) // this annotation executes below method when UserNotFoundException is thrown
    public ResponseEntity<ExceptionWrapper> userNotFoundException(UserNotFoundException exception) {

        exception.printStackTrace();

        String message = exception.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionWrapper(message, HttpStatus.NOT_FOUND.value()));

    }








}
