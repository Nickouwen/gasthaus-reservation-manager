package com.gasthaus.reservation_service.exception;

public class OverlapsBlockedDatesException extends RuntimeException {
    public OverlapsBlockedDatesException(String message) {
        super(message);
    }
}
