package com.gasthaus.reservation_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getReservations() {
        List<ReservationResponseDTO> reservations = reservationService.getReservations();
        return ResponseEntity.ok().body(reservations);
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO) {
        ReservationResponseDTO reservationResponseDTO = reservationService.createReservation(reservationRequestDTO);
        return ResponseEntity.ok().body(reservationResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservation(
        @PathVariable UUID id,
        @Validated({Default.class}) @RequestBody ReservationRequestDTO reservationRequestDTO) {
        ReservationResponseDTO reservationResponseDTO = reservationService.updateReservation(id, reservationRequestDTO);

        return ResponseEntity.ok().body(reservationResponseDTO);
    }
}
