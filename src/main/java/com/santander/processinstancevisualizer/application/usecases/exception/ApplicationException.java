package com.santander.processinstancevisualizer.application.usecases.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class ApplicationException extends RuntimeException {

    private final HttpStatus status;
    private final Throwable cause;

    public ApplicationException(HttpStatus status, String message, Throwable cause) {
        super(message);
        this.status = status;
        this.cause = cause;
    }
}
