package com.gasthaus.reservation_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gasthaus.reservation_service.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

}
