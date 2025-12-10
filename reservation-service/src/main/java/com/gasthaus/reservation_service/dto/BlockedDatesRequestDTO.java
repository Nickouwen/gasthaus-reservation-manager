package com.gasthaus.reservation_service.dto;

import jakarta.validation.constraints.NotBlank;

public class BlockedDatesRequestDTO {
    @NotBlank(message = "Start date is required")
    private String startDate;

    @NotBlank(message = "End date is required")
    private String endDate;

    @NotBlank(message = "Reason is required")
    private String reason;

    @NotBlank(message = "Block type is required")
    private String blockType;

    // Getters and Setters

    public @NotBlank(message = "Start date is required") String getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotBlank(message = "Start date is required") String startDate) {
        this.startDate = startDate;
    }

    public @NotBlank(message = "End date is required") String getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotBlank(message = "End date is required") String endDate) {
        this.endDate = endDate;
    }

    public @NotBlank(message = "Reason is required") String getReason() {
        return reason;
    }

    public void setReason(@NotBlank(message = "Reason is required") String reason) {
        this.reason = reason;
    }

    public @NotBlank(message = "Block type is required") String getBlockType() {
        return blockType;
    }

    public void setBlockType(@NotBlank(message = "Block type is required") String blockType) {
        this.blockType = blockType;
    }
}
