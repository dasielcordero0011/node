package com.santander.processinstancevisualizer.infrastructure.error;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

import jakarta.validation.constraints.NotNull;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.santander.processinstancevisualizer.application.usecases.exception.ApplicationException;
import com.santander.processinstancevisualizer.domain.model.ErrorModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private static final String SPRING_APPLICATION_NAME = "spring.application.name";
    public static final String ERROR = "ERROR";

    private final Environment environment;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorModel> handleApplicationException(ApplicationException ex) {
        return getErrorModelResponseEntity(
                ex.getStatus().value(),
                ex.getMessage(),
                "Error occurred while processing the request",
                ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handleGenericException(Exception ex) {
        return getErrorModelResponseEntity(
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred",
                ex);
    }

    @NotNull
    private ResponseEntity<ErrorModel> getErrorModelResponseEntity(
            int code, String message, String description, Exception ex) {
        final var errorModel = ErrorModel.builder()
                .code(code)
                .description(description)
                .level(ERROR)
                .message(message)
                .build();

        final var appName = getApplicationName();
        log.error("Error occurred: service {}, error {} cause: {}", appName, errorModel, ex);

        return status(INTERNAL_SERVER_ERROR)
                .body(errorModel);
    }

    private String getApplicationName() {
        return environment.getProperty(SPRING_APPLICATION_NAME);
    }
}
