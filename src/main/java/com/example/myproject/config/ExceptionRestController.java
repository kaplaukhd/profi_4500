package com.example.myproject.config;

import com.example.myproject.exception.UserNotAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionRestController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> globalException(RuntimeException exception) {
        Response error = new Response(
                exception.getMessage(),
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<Response> notAuthenticatedException(UserNotAuthenticatedException exception) {
        Response error = new Response(
                exception.getMessage(),
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN,
                HttpStatus.FORBIDDEN.value()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
