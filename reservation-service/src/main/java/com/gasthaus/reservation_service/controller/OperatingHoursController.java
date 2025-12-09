package com.gasthaus.reservation_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasthaus.reservation_service.dto.OperatingHoursResponseDTO;
import com.gasthaus.reservation_service.service.OperatingHoursService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/operating-hours")
@Tag(name = "Operating Hours", description = "APIs for managing operating hours")
public class OperatingHoursController {
    private final OperatingHoursService operatingHoursService;

    public OperatingHoursController(OperatingHoursService operatingHoursService) {
        this.operatingHoursService = operatingHoursService;
    }

    @GetMapping
    public ResponseEntity<List<OperatingHoursResponseDTO>> getAllOperatingHours() {
        List<OperatingHoursResponseDTO> operatingHours = operatingHoursService.getAllOperatingHours();
        return ResponseEntity.ok().body(operatingHours);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperatingHoursResponseDTO> getOperatingHoursById(@PathVariable String id) {
        OperatingHoursResponseDTO operatingHours = operatingHoursService.getOperatingHoursById(id);
        return ResponseEntity.ok().body(operatingHours);
    }

}
