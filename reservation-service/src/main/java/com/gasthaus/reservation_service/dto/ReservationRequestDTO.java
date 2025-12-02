package com.gasthaus.reservation_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ReservationRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Reservation date and time is required")
    private String reservationDateTime;

    @NotNull(message = "Number of guests is required")
    @Positive(message = "Number of guests must be positive")
    private Integer numberOfGuests;

    // Setters and Getters

    public @NotBlank(message = "Name is required") @Size(max = 100, message = "Name must not exceed 100 characters") String getName() {
        return name;
    }

    public void setName(
        @NotBlank(message = "Name is required") @Size(max = 100, message = "Name must not exceed 100 characters") String name
    ) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(
        @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email
    ) {
        this.email = email;
    }

    // TODO: Add validation to ensure reservationDateTime is only allowed within business hours; Currently there is no entity relating to business hours, but this will be a must
    public @NotBlank(message = "Reservation date and time is required") String getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(
        @NotBlank(message = "Reservation date and time is required") String reservationDateTime
    ) {
        this.reservationDateTime = reservationDateTime;
    }

    // TODO: Add validation that checks a restaurant's business rules for number of guests per reservation
    public @NotNull(message = "Number of guests is required") @Positive(message = "Number of guests must be positive") @Max(value = 8, message = "Number of guests must not exceed 8") Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(
        @NotNull(message = "Number of guests is required") @Positive(message = "Number of guests must be positive") @Max(value = 8, message = "Number of guests must not exceed 8") Integer numberOfGuests
    ) {
        this.numberOfGuests = numberOfGuests;
    }
}
