package com.gasthaus.reservation_service.exception;

public class BlockedDatesNotFoundException extends RuntimeException {
    public BlockedDatesNotFoundException(String message) {
        super(message);
    }
}
