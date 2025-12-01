package com.gasthaus.reservation_service.dto;

import jakarta.validation.constraints.Email;
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
    private int numberOfGuests;

    @NotBlank(message = "Created at is required")
    private String createdAt;

    @NotBlank(message = "Updated at is required")
    private String updatedAt;

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

    public @NotBlank(message = "Reservation date and time is required") String getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(
        @NotBlank(message = "Reservation date and time is required") String reservationDateTime
    ) {
        this.reservationDateTime = reservationDateTime;
    }

    public @NotNull(message = "Number of guests is required") @Positive(message = "Number of guests must be positive") int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(
        @NotNull(message = "Number of guests is required") @Positive(message = "Number of guests must be positive") int numberOfGuests
    ) {
        this.numberOfGuests = numberOfGuests;
    }

    public @NotBlank(message = "Created at is required") String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
        @NotBlank(message = "Created at is required") String createdAt
    ) {
        this.createdAt = createdAt;
    }

    public @NotBlank(message = "Updated at is required") String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(
        @NotBlank(message = "Updated at is required") String updatedAt
    ) {
        this.updatedAt = updatedAt;
    }
}
