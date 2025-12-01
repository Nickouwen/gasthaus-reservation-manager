package com.gasthaus.reservation_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gasthaus.reservation_service.dto.ReservationRequestDTO;
import com.gasthaus.reservation_service.dto.ReservationResponseDTO;
import com.gasthaus.reservation_service.mapper.ReservationMapper;
import com.gasthaus.reservation_service.models.Reservation;
import com.gasthaus.reservation_service.repository.ReservationRepository;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationResponseDTO> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(ReservationMapper::toDTO).toList();
    }

    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {
        Reservation newReservation = reservationRepository.save(
            ReservationMapper.toModel(reservationRequestDTO)
        );

        return ReservationMapper.toDTO(newReservation);
    }
}
