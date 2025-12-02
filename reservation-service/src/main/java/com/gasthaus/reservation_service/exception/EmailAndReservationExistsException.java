package com.gasthaus.reservation_service.exception;

public class EmailAndReservationExistsException extends RuntimeException {
    public EmailAndReservationExistsException(String message) {
        super(message);
    }
}
