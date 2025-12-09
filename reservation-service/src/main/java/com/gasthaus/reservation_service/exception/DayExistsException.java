package com.gasthaus.reservation_service.exception;

public class DayExistsException extends RuntimeException {
    public DayExistsException(String message) {
        super(message);
    }
}
