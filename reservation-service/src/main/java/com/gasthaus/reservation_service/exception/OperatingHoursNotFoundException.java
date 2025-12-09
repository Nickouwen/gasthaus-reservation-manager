package com.gasthaus.reservation_service.exception;

public class OperatingHoursNotFoundException extends RuntimeException {
    public OperatingHoursNotFoundException(String message) {
        super(message);
    }
}
