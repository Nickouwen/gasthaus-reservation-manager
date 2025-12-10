package com.gasthaus.reservation_service.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gasthaus.reservation_service.models.BlockedDates;

@Repository
public interface BlockedDatesRepository extends JpaRepository<BlockedDates, UUID> {

    @Query("SELECT bd FROM BlockedDates bd WHERE " +
           "(bd.startDateTime <= :endTime AND bd.endDateTime >= :startTime)")
    List<BlockedDates> findOverlappingPeriods(
        @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime
    );

     @Query("SELECT bd FROM BlockedDates bd WHERE " +
           "bd.startDateTime >= :startDate AND bd.endDateTime <= :endDate " +
           "ORDER BY bd.startDateTime")
    List<BlockedDates> findByDateRange(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
