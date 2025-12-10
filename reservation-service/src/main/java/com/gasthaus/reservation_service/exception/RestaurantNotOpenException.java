package com.gasthaus.reservation_service.exception;

public class RestaurantNotOpenException extends RuntimeException {
    public RestaurantNotOpenException(String message) {
        super(message);
    }
}
