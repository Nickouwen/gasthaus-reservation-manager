
package com.gasthaus.reservation_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gasthaus.reservation_service.models.OperatingHours;

@Repository
public interface OperatingHoursRepository extends JpaRepository<OperatingHours, UUID> {

    boolean existsByDay(String day);
    
    @Query("SELECT oh FROM OperatingHours oh WHERE oh.day = :day")
    Optional<OperatingHours> findByDay(@Param("day") String day);
}
