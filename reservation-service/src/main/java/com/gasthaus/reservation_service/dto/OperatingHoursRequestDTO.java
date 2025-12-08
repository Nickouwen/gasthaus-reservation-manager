package com.gasthaus.reservation_service.dto;

import jakarta.validation.constraints.NotBlank;

public class OperatingHoursRequestDTO {
    @NotBlank(message = "Day is required")
    private String day;

    @NotBlank(message = "Open time is required")
    private String openTime;

    @NotBlank(message = "Close time is required")
    private String closeTime;
}
