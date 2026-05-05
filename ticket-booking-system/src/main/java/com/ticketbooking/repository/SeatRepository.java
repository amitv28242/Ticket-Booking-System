package com.ticketbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticketbooking.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByTransportId(Long transportId);
    
    @Query("SELECT s FROM Seat s WHERE s.transport.id = :transportId AND s.isAvailable = true")
    List<Seat> findAvailableSeatsByTransportId(@Param("transportId") Long transportId);
    
    @Query("SELECT s FROM Seat s WHERE s.transport.id = :transportId AND s.seatNumber = :seatNumber")
    Optional<Seat> findByTransportIdAndSeatNumber(@Param("transportId") Long transportId, @Param("seatNumber") String seatNumber);
}