package com.ticketbooking.repository;

import com.ticketbooking.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByTransportIdAndJourneyDate(Long transportId, LocalDate journeyDate);
    
    @Query("SELECT s FROM Schedule s WHERE s.transport.id = :transportId AND s.journeyDate = :journeyDate")
    Optional<Schedule> findByTransportIdAndJourneyDateSingle(
            @Param("transportId") Long transportId, 
            @Param("journeyDate") LocalDate journeyDate);
    
    List<Schedule> findByJourneyDate(LocalDate journeyDate);
}