package com.cydeo.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionWrapper {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;

    public ExceptionWrapper(String message, Integer status) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
