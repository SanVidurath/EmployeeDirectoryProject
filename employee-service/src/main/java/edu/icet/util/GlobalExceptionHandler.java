package edu.icet.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String ERROR = "error";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_TYPE = "application/json";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header(CONTENT_TYPE, APPLICATION_TYPE)
                .body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        Throwable rootCause = ex.getRootCause();
        String message = Objects.requireNonNullElse(rootCause, ex).getMessage();


        if (message.contains("Duplicate entry")) {
            errors.put(ERROR, "email already exists");
        } else {
            errors.put(ERROR, "Data integrity violation");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header(CONTENT_TYPE, APPLICATION_TYPE)
                .body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();

        if (ex.getCause() instanceof InvalidFormatException cause) {

            if (cause.getTargetType().isEnum()) {
                error.put("department", "Department must be one of the following: HR, IT, FINANCE, OPERATIONS");
            } else {
                error.put(ERROR, "Invalid input format");
            }
        } else {
            error.put(ERROR, "Invalid input");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header(CONTENT_TYPE, APPLICATION_TYPE)
                .body(error);
    }

}
