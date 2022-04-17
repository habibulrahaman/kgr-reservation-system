package com.kgr.reservationsystem.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;

@ControllerAdvice
@Slf4j
public class ReservationControllerHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        log.error("Exception Occurred while accepting the request - {}", request, exception);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {EntityNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<Void> entityNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {DateTimeException.class})
    public ResponseEntity<Void> invalidDateTimeException() {
        return ResponseEntity.badRequest().build();
    }

}
