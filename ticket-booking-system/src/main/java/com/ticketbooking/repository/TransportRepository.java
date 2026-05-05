package com.ticketbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticketbooking.entity.Transport;
import com.ticketbooking.enums.TransportType;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    List<Transport> findByType(TransportType type);
    
    @Query("SELECT t FROM Transport t WHERE t.type = :type AND t.source = :source AND t.destination = :destination")
    List<Transport> findByTypeAndSourceAndDestination(
            @Param("type") TransportType type,
            @Param("source") String source,
            @Param("destination") String destination);
    
    Optional<Transport> findByNumber(String number);
}
