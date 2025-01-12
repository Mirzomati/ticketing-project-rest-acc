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

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionWrapper> UserAlreadyExistsException(UserAlreadyExistsException exception) {

        exception.printStackTrace();

        String message = exception.getMessage();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionWrapper(message, HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    public ResponseEntity<ExceptionWrapper> genericException(Throwable exception) {

        exception.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionWrapper("An error occurred, please try again later", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }








}
