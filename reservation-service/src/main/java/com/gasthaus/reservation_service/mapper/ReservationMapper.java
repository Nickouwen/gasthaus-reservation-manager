package com.gasthaus.reservation_service.mapper;

import java.time.LocalDateTime;

import com.gasthaus.reservation_service.dto.ReservationRequestDTO;
import com.gasthaus.reservation_service.dto.ReservationResponseDTO;
import com.gasthaus.reservation_service.models.Reservation;

public class ReservationMapper {
    public static ReservationResponseDTO toDTO(Reservation reservation) {
        ReservationResponseDTO reservationDTO = new ReservationResponseDTO();
        reservationDTO.setId(reservation.getId().toString());
        reservationDTO.setName(reservation.getName());
        reservationDTO.setEmail(reservation.getEmail());
        reservationDTO.setReservationDateTime(reservation.getReservationDateTime().toString());
        reservationDTO.setNumberOfGuests(reservation.getNumberOfGuests());
        return reservationDTO;
    }

    public static Reservation toModel(ReservationRequestDTO reservationRequestDTO) {
        Reservation reservation = new Reservation();
        reservation.setName(reservationRequestDTO.getName());
        reservation.setEmail(reservationRequestDTO.getEmail());
        reservation.setReservationDateTime(LocalDateTime.parse(reservationRequestDTO.getReservationDateTime()));
        reservation.setNumberOfGuests(reservationRequestDTO.getNumberOfGuests());
        return reservation;
    }
}
