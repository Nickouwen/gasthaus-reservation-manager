package com.gasthaus.reservation_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.gasthaus.reservation_service.models.Reservation;

import reservation.events.ReservationEvent;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Reservation reservation) {
        ReservationEvent event = ReservationEvent.newBuilder()
                .setReservationId(reservation.getId().toString())
                .setName(reservation.getName())
                .setEmail(reservation.getEmail())
                .setNumberOfGuests(reservation.getNumberOfGuests())
                .setTimestamp(reservation.getReservationDateTime().toString())
                .setEventType("RESERVATION_CREATED")
                .build();

        kafkaTemplate.send("reservation-events", event.toByteArray());
    }
}
