package com.cydeo.exception;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ExceptionWrapper {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;

    private Integer errorCode;
    private List<ValidationException> validationExceptionList;

    public ExceptionWrapper(String message, Integer status) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
