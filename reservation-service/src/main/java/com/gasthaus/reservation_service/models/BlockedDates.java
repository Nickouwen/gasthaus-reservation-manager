package com.gasthaus.reservation_service.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class BlockedDates {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    @NotNull
    private String reason;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BlockType blockType;

    @NotNull
    @CreatedDate
    private LocalDateTime createdAt;
}
