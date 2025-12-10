package com.gasthaus.reservation_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(
        GlobalExceptionHandler.class
    );

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );

            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(errors);
    }

    @ExceptionHandler(EmailAndReservationExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAndReservationExistsException(
        EmailAndReservationExistsException ex) {
            log.warn("Email address already has a reservation at the specified date and time: {}", ex.getMessage());

            Map<String, String> error = new HashMap<>();
            // Example explicitly sets the error message. Not sure if this is correct or will need to be changed.
            // ex. error.put("message", "A reservation with this email and date/time already exists.");
            error.put("message", ex.getMessage());

            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(error);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleReservationNotFoundException(
        ReservationNotFoundException ex) {
            log.warn("Reservation not found: {}", ex.getMessage());

            Map<String, String> error = new HashMap<>();
            error.put("message", ex.getMessage());

            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(error);
    }

    @ExceptionHandler(DayExistsException.class)
    public ResponseEntity<Map<String, String>> handleDayExistsException(
        DayExistsException ex) {
            log.warn("Day already exists: {}", ex.getMessage());

            Map<String, String> error = new HashMap<>();
            error.put("message", ex.getMessage());

            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(error);
    }

    @ExceptionHandler(OperatingHoursNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOperatingHoursNotFoundException(
        OperatingHoursNotFoundException ex) {
            log.warn("Operating hours not found: {}", ex.getMessage());

            Map<String, String> error = new HashMap<>();
            error.put("message", ex.getMessage());

            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(error);
    }
}
