package com.demo.first.app.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // EXCEPTION HANDLING METHOD!
    @ExceptionHandler({ UserNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<Map<String, Object>> handleIllegalArgsException(
            Exception exception
    ){
        logger.error("Error when finding User --> "+ exception);
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Bad Request");
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", exception.getMessage());
        errorResponse.put("status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // EXCEPTION HANDLING METHOD!
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException exception
    ){
        logger.error("Request Method Not Allowed --> "+ exception);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Method not allowed.");
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", "Method not allowed on this route.");
        errorResponse.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

}
