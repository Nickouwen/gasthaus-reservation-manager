package com.gasthaus.reservation_service.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gasthaus.reservation_service.dto.ReservationRequestDTO;
import com.gasthaus.reservation_service.dto.ReservationResponseDTO;
import com.gasthaus.reservation_service.exception.EmailAndReservationExistsException;
import com.gasthaus.reservation_service.exception.OverlapsBlockedDatesException;
import com.gasthaus.reservation_service.exception.ReservationNotFoundException;
import com.gasthaus.reservation_service.exception.RestaurantNotOpenException;
import com.gasthaus.reservation_service.kafka.KafkaProducer;
import com.gasthaus.reservation_service.mapper.ReservationMapper;
import com.gasthaus.reservation_service.models.BlockedDates;
import com.gasthaus.reservation_service.models.Reservation;
import com.gasthaus.reservation_service.repository.BlockedDatesRepository;
import com.gasthaus.reservation_service.repository.OperatingHoursRepository;
import com.gasthaus.reservation_service.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    private final BlockedDatesRepository blockedDatesRepository;

    private final OperatingHoursRepository operatingHoursRepository;

    private final KafkaProducer kafkaProducer;

    public ReservationService(ReservationRepository reservationRepository, BlockedDatesRepository blockedDatesRepository, OperatingHoursRepository operatingHoursRepository, KafkaProducer kafkaProducer) {
        this.reservationRepository = reservationRepository;
        this.blockedDatesRepository = blockedDatesRepository;
        this.operatingHoursRepository = operatingHoursRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public List<ReservationResponseDTO> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(ReservationMapper::toDTO).toList();
    }

    public ReservationResponseDTO getReservationById(UUID id) {
        Reservation reservation = reservationRepository
            .findById(id)
            .orElseThrow(
                () -> new ReservationNotFoundException("Reservation not found with id: " + id)
            );

        return ReservationMapper.toDTO(reservation);
    }

    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {
        if(reservationRepository.existsByEmailAndReservationDateTime(reservationRequestDTO.getEmail(), LocalDateTime.parse(reservationRequestDTO.getReservationDateTime()))) {
            throw new EmailAndReservationExistsException("A reservation with this email and date/time already exists: " + reservationRequestDTO.getEmail() + " " + reservationRequestDTO.getReservationDateTime());
        }

        List<BlockedDates> blockedDates = blockedDatesRepository.findOverlappingPeriods(LocalDateTime.parse(reservationRequestDTO.getReservationDateTime()));
        if(!blockedDates.isEmpty()) {
            throw new OverlapsBlockedDatesException("Reservation overlaps blocked date: " + blockedDates.get(0).getReason());
        }

        LocalDateTime reservationDateTime = LocalDateTime.parse(reservationRequestDTO.getReservationDateTime());

        String dayOfWeek = reservationDateTime.getDayOfWeek()
            .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        LocalTime reservationTime = reservationDateTime.toLocalTime();
            
        if(!operatingHoursRepository.isWithinOperatingHours(dayOfWeek, reservationTime)) {
            throw new RestaurantNotOpenException("Reservation time is outside of operating hours.");
        }

        Reservation newReservation = reservationRepository.save(
            ReservationMapper.toModel(reservationRequestDTO)
        );

        kafkaProducer.sendEvent(newReservation);

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
