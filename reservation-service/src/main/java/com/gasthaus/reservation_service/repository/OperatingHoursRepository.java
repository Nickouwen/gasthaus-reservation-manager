
package com.gasthaus.reservation_service.repository;

import java.time.LocalTime;
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

    @Query("SELECT CASE WHEN COUNT(oh) > 0 THEN true ELSE false END " +
       "FROM OperatingHours oh WHERE " +
       "oh.day = :dayOfWeek AND " +
       "oh.openTime <= :reservationTime AND " +
       "oh.closeTime >= :reservationTime")
    boolean isWithinOperatingHours(
    @Param("dayOfWeek") String dayOfWeek,
    @Param("reservationTime") LocalTime reservationTime
    );
}
