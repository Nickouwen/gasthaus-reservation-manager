package com.gasthaus.reservation_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gasthaus.reservation_service.dto.ReservationRequestDTO;
import com.gasthaus.reservation_service.dto.ReservationResponseDTO;
import com.gasthaus.reservation_service.exception.EmailAndReservationExistsException;
import com.gasthaus.reservation_service.exception.ReservationNotFoundException;
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
        if(reservationRepository.existsByEmailAndReservationDateTime(reservationRequestDTO.getEmail(), LocalDateTime.parse(reservationRequestDTO.getReservationDateTime()))) {
            throw new EmailAndReservationExistsException("A reservation with this email and date/time already exists: " + reservationRequestDTO.getEmail() + " " + reservationRequestDTO.getReservationDateTime());
        }

        Reservation newReservation = reservationRepository.save(
            ReservationMapper.toModel(reservationRequestDTO)
        );

        return ReservationMapper.toDTO(newReservation);
    }

    public ReservationResponseDTO updateReservation(UUID id, ReservationRequestDTO reservationRequestDTO) {
        Reservation reservation = reservationRepository
        .findById(id)
        .orElseThrow(
            () -> new ReservationNotFoundException("Reservation not found with id: " + id)
        );

        reservation.setName(reservationRequestDTO.getName());
        reservation.setEmail(reservationRequestDTO.getEmail());
        reservation.setReservationDateTime(LocalDateTime.parse(reservationRequestDTO.getReservationDateTime()));
        reservation.setNumberOfGuests(reservationRequestDTO.getNumberOfGuests());

        Reservation updatedReservation = reservationRepository.save(reservation);

        return ReservationMapper.toDTO(updatedReservation);
    }

    public void deleteReservation(UUID id) {
        reservationRepository.deleteById(id);
    }
}
