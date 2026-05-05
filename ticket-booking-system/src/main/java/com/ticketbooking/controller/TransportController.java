package com.ticketbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbooking.repository.BusRepository;
import com.ticketbooking.repository.FlightRepository;
import com.ticketbooking.repository.MetroRepository;
import com.ticketbooking.repository.TrainRepository;

@RestController
@RequestMapping("/api/transports")
public class TransportController {

    private final TrainRepository trainRepository;
    private final FlightRepository flightRepository;
    private final BusRepository busRepository;
    private final MetroRepository metroRepository;

    // Manual Constructor
    public TransportController(
            TrainRepository trainRepository,
            FlightRepository flightRepository,
            BusRepository busRepository,
            MetroRepository metroRepository) {

        this.trainRepository = trainRepository;
        this.flightRepository = flightRepository;
        this.busRepository = busRepository;
        this.metroRepository = metroRepository;
    }

    @GetMapping("/trains")
    public ResponseEntity<?> getAllTrains() {
        return ResponseEntity.ok(trainRepository.findAll());
    }

    @GetMapping("/flights")
    public ResponseEntity<?> getAllFlights() {
        return ResponseEntity.ok(flightRepository.findAll());
    }

    @GetMapping("/buses")
    public ResponseEntity<?> getAllBuses() {
        return ResponseEntity.ok(busRepository.findAll());
    }

    @GetMapping("/metros")
    public ResponseEntity<?> getAllMetros() {
        return ResponseEntity.ok(metroRepository.findAll());
    }
}