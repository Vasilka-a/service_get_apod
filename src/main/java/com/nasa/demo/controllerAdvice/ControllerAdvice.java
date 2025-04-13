package com.nasa.demo.controllerAdvice;

import com.nasa.demo.exception.InvalidInputException;
import com.nasa.demo.exception.InvalidURLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidURLException.class)
    public ResponseEntity<Error> handlerInvalidUrl(InvalidURLException e) {
        Error error = new Error(e.getMessage());
        log.error("Error message: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Error> handlerInvalidInput(InvalidInputException e) {
        Error error = new Error(e.getMessage());
        log.error("Error message: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error);
    }
}
