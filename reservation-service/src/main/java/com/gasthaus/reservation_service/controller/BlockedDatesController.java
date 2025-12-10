package com.gasthaus.reservation_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasthaus.reservation_service.dto.BlockedDatesRequestDTO;
import com.gasthaus.reservation_service.dto.BlockedDatesResponseDTO;
import com.gasthaus.reservation_service.service.BlockedDatesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/blocked-dates")
@Tag(name = "Blocked Dates", description = "APIs for managing blocked dates")
public class BlockedDatesController {
    private final BlockedDatesService blockedDatesService;

    public BlockedDatesController(BlockedDatesService blockedDatesService) {
        this.blockedDatesService = blockedDatesService;
    }

    @GetMapping
    @Operation(summary = "Get all blocked dates")
    public ResponseEntity<List<BlockedDatesResponseDTO>> getAllBlockedDates() {
        List<BlockedDatesResponseDTO> blockedDates = blockedDatesService.getAllBlockedDates();
        return ResponseEntity.ok().body(blockedDates);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get blocked dates by ID")
    public ResponseEntity<BlockedDatesResponseDTO> getBlockedDatesById(@PathVariable UUID id) {
        BlockedDatesResponseDTO blockedDates = blockedDatesService.getBlockedDatesById(id);
        return ResponseEntity.ok().body(blockedDates);
    }

    @PostMapping
    @Operation(summary = "Create new blocked dates")
    public ResponseEntity<BlockedDatesResponseDTO> createBlockedDates(@Valid @RequestBody BlockedDatesRequestDTO requestDTO) {
        BlockedDatesResponseDTO blockedDates = blockedDatesService.createBlockedDates(requestDTO);
        return ResponseEntity.ok().body(blockedDates);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete blocked dates by ID")
    public ResponseEntity<Void> deleteBlockedDates(@PathVariable UUID id) {
        blockedDatesService.deleteBlockedDates(id);
        return ResponseEntity.noContent().build();
    }
}
