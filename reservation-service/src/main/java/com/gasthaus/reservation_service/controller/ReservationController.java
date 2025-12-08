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

import com.gasthaus.reservation_service.dto.ReservationRequestDTO;
import com.gasthaus.reservation_service.dto.ReservationResponseDTO;
import com.gasthaus.reservation_service.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservation", description = "APIs for managing reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @Operation(summary = "Get all reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getReservations() {
        List<ReservationResponseDTO> reservations = reservationService.getReservations();
        return ResponseEntity.ok().body(reservations);
    }

    @PostMapping
    @Operation(summary = "Create a new reservation")
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO) {
        ReservationResponseDTO reservationResponseDTO = reservationService.createReservation(reservationRequestDTO);
        return ResponseEntity.ok().body(reservationResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing reservation")
    public ResponseEntity<ReservationResponseDTO> updateReservation(
        @PathVariable UUID id,
        @Valid @RequestBody ReservationRequestDTO reservationRequestDTO) {
        ReservationResponseDTO reservationResponseDTO = reservationService.updateReservation(id, reservationRequestDTO);

        return ResponseEntity.ok().body(reservationResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a reservation")
    public ResponseEntity<Void> deleteReservation(
        @PathVariable UUID id) {
        reservationService.deleteReservation(id);

        return ResponseEntity.noContent().build();
    }
}
