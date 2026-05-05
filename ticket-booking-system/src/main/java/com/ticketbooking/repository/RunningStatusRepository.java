package com.ticketbooking.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticketbooking.entity.RunningStatus;
import com.ticketbooking.enums.TransportType;

@Repository
public interface RunningStatusRepository extends JpaRepository<RunningStatus, Long> {
    
    @Query("SELECT r FROM RunningStatus r WHERE r.transportNumber = :number AND r.transportType = :type AND r.journeyDate = :date")
    Optional<RunningStatus> findByTransportNumberAndTypeAndDate(
            @Param("number") String number,
            @Param("type") TransportType type,
            @Param("date") LocalDate date);
    
    List<RunningStatus> findByTransportTypeAndJourneyDate(TransportType type, LocalDate date);
    
    @Query("SELECT r FROM RunningStatus r WHERE r.transportNumber = :number ORDER BY r.lastUpdated DESC")
    List<RunningStatus> findByTransportNumberOrderByLastUpdatedDesc(@Param("number") String number);
}
