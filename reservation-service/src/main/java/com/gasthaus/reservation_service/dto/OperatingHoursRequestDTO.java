package com.gasthaus.reservation_service.dto;

import jakarta.validation.constraints.NotBlank;

public class OperatingHoursRequestDTO {
    @NotBlank(message = "Day is required")
    private String day;

    @NotBlank(message = "Open time is required")
    private String openTime;

    @NotBlank(message = "Close time is required")
    private String closeTime;

    // Getters and Setters

    public @NotBlank(message = "Day is required") String getDay() {
        return day;
    }

    public void setDay(@NotBlank(message = "Day is required") String day) {
        this.day = day;
    }

    public @NotBlank(message = "Open time is required") String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(@NotBlank(message = "Open time is required") String openTime) {
        this.openTime = openTime;
    }

    public @NotBlank(message = "Close time is required") String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(@NotBlank(message = "Close time is required") String closeTime) {
        this.closeTime = closeTime;
    }
}
