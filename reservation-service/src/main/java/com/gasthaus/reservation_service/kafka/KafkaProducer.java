package com.gasthaus.reservation_service.kafka;

import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.gasthaus.reservation_service.models.Reservation;

import reservation.events.ReservationEvent;

@Service
public class KafkaProducer {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(KafkaProducer.class);

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
        try {
            kafkaTemplate.send("reservation", event.toByteArray());
        } catch (Exception e) {
            log.error("Failed to build ReservationEvent: {}", e.getMessage());
        }
    }
}
