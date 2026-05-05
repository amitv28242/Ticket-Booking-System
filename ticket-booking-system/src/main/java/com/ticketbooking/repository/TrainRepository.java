package com.ticketbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticketbooking.entity.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
    Optional<Train> findByNumber(String number);
    
    @Query("SELECT t FROM Train t WHERE t.source = :source AND t.destination = :destination")
    List<Train> findBySourceAndDestination(@Param("source") String source, @Param("destination") String destination);
}