package com.santander.processinstancevisualizer.infrastructure.adapters.output.reactive.exceptions;

import org.springframework.http.HttpStatus;

import com.santander.processinstancevisualizer.application.usecases.exception.ApplicationException;

import lombok.Builder;
import lombok.Getter;

@Getter
public class APIOutputAdapterException extends ApplicationException {

    @Builder
    public APIOutputAdapterException(HttpStatus httpStatus, String message, Throwable cause) {
        super(httpStatus, message, cause);
    }
}
