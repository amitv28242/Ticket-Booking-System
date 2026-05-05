package com.ticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticketbooking.entity.Metro;

@Repository
public interface MetroRepository extends JpaRepository<Metro, Long> {
    @Query("SELECT m FROM Metro m WHERE m.source = :source AND m.destination = :destination")
    List<Metro> findBySourceAndDestination(@Param("source") String source, @Param("destination") String destination);
}