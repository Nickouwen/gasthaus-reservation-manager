package com.gasthaus.reservation_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasthaus.reservation_service.dto.OperatingHoursRequestDTO;
import com.gasthaus.reservation_service.dto.OperatingHoursResponseDTO;
import com.gasthaus.reservation_service.service.OperatingHoursService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/operating-hours")
@Tag(name = "Operating Hours", description = "APIs for managing operating hours")
public class OperatingHoursController {
    private final OperatingHoursService operatingHoursService;

    public OperatingHoursController(OperatingHoursService operatingHoursService) {
        this.operatingHoursService = operatingHoursService;
    }

    @GetMapping
    @Operation(summary = "Get all operating hours")
    public ResponseEntity<List<OperatingHoursResponseDTO>> getAllOperatingHours() {
        List<OperatingHoursResponseDTO> operatingHours = operatingHoursService.getAllOperatingHours();
        return ResponseEntity.ok().body(operatingHours);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get operating hours by ID")
    public ResponseEntity<OperatingHoursResponseDTO> getOperatingHoursById(@PathVariable UUID id) {
        OperatingHoursResponseDTO operatingHours = operatingHoursService.getOperatingHoursById(id);
        return ResponseEntity.ok().body(operatingHours);
    }

    @GetMapping("/day/{day}")
    @Operation(summary = "Get operating hours by day")
    public ResponseEntity<OperatingHoursResponseDTO> getOperatingHoursByDay(@PathVariable String day) {
        OperatingHoursResponseDTO operatingHours = operatingHoursService.getOperatingHoursByDay(day);
        return ResponseEntity.ok().body(operatingHours);
    }

    @PostMapping
    @Operation(summary = "Create new operating hours")
    public ResponseEntity<OperatingHoursResponseDTO> createOperatingHours(@Valid @RequestBody OperatingHoursRequestDTO requestDTO) {
        OperatingHoursResponseDTO operatingHours = operatingHoursService.createOperatingHours(requestDTO);
        return ResponseEntity.ok().body(operatingHours);   
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing operating hours")
    public ResponseEntity<OperatingHoursResponseDTO> updateOperatingHours(
        @PathVariable UUID id,
        @Valid @RequestBody OperatingHoursRequestDTO requestDTO) {
        OperatingHoursResponseDTO operatingHours = operatingHoursService.updateOperatingHours(
            id,
            requestDTO
        );
        return ResponseEntity.ok().body(operatingHours);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete operating hours by ID")
    public ResponseEntity<Void> deleteOperatingHours(@PathVariable UUID id) {
        operatingHoursService.deleteOperatingHours(id);
        return ResponseEntity.noContent().build();
    }
}
