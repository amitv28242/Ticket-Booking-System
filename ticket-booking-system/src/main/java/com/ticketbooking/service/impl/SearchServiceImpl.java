package com.ticketbooking.service.impl;

import com.ticketbooking.dto.*;
import com.ticketbooking.entity.*;
import com.ticketbooking.repository.*;
import com.ticketbooking.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final TrainRepository trainRepository;
	private final FlightRepository flightRepository;
	private final BusRepository busRepository;
	private final MetroRepository metroRepository;
	
	// ✅ MANUAL CONSTRUCTOR
    public SearchServiceImpl(
            TrainRepository trainRepository,
            FlightRepository flightRepository,
            BusRepository busRepository,
            MetroRepository metroRepository
    ) {
        this.trainRepository = trainRepository;
        this.flightRepository = flightRepository;
        this.busRepository = busRepository;
        this.metroRepository = metroRepository;
    }

	@Override
	public ApiResponse<List<SearchResponse>> searchTransports(SearchRequest request) {
		List<SearchResponse> results = new ArrayList<>();

		switch (request.getTransportType()) {
		case TRAIN:
			results = trainRepository.findBySourceAndDestination(request.getSource(), request.getDestination()).stream()
					.map(this::mapTrainToResponse).collect(Collectors.toList());
			break;
		case FLIGHT:
			results = flightRepository.findBySourceAndDestination(request.getSource(), request.getDestination())
					.stream().map(this::mapFlightToResponse).collect(Collectors.toList());
			break;
		case BUS:
			results = busRepository.findBySourceAndDestination(request.getSource(), request.getDestination()).stream()
					.map(this::mapBusToResponse).collect(Collectors.toList());
			break;
		case METRO:
			results = metroRepository.findBySourceAndDestination(request.getSource(), request.getDestination()).stream()
					.map(this::mapMetroToResponse).collect(Collectors.toList());
			break;
		}

		return ApiResponse.success(results, "Search results retrieved");
	}

	@Override
	public ApiResponse<List<String>> getPopularSources() {
		List<String> sources = List.of("Delhi", "Mumbai", "Bangalore", "Chennai", "Kolkata", "Hyderabad", "Pune",
				"Jaipur");
		return ApiResponse.success(sources, "Popular sources retrieved");
	}

	@Override
	public ApiResponse<List<String>> getPopularDestinations() {
		List<String> destinations = List.of("Mumbai", "Delhi", "Bangalore", "Goa", "Chennai", "Kolkata", "Hyderabad",
				"Pune");
		return ApiResponse.success(destinations, "Popular destinations retrieved");
	}

	private SearchResponse mapTrainToResponse(Train train) {
		return SearchResponse.builder().transportId(train.getId()).name(train.getName()).number(train.getNumber())
				.type("TRAIN").source(train.getSource()).destination(train.getDestination())
				.departureTime(train.getDepartureTime()).arrivalTime(train.getArrivalTime())
				.duration(train.getDuration()).distanceKm(train.getDistanceKm())
				.baseFare(java.math.BigDecimal.valueOf(train.getDistanceKm() * 2.5)).availableSeats(50)
				.amenities(List.of("WiFi", "Charging", "Food")).status("ON_TIME").build();
	}

	private SearchResponse mapFlightToResponse(Flight flight) {
		return SearchResponse.builder().transportId(flight.getId()).name(flight.getAirline())
				.number(flight.getFlightNumber()).type("FLIGHT").source(flight.getSource())
				.destination(flight.getDestination()).departureTime(flight.getDepartureTime())
				.arrivalTime(flight.getArrivalTime()).duration(flight.getDuration()).distanceKm(flight.getDistanceKm())
				.baseFare(java.math.BigDecimal.valueOf(flight.getDistanceKm() * 8.0)).availableSeats(30)
				.amenities(List.of("WiFi", "Meals", "Entertainment")).status("ON_TIME").build();
	}

	private SearchResponse mapBusToResponse(Bus bus) {
		return SearchResponse.builder().transportId(bus.getId()).name(bus.getOperator()).number(bus.getNumber())
				.type("BUS").source(bus.getSource()).destination(bus.getDestination())
				.departureTime(bus.getDepartureTime()).arrivalTime(bus.getArrivalTime()).duration(bus.getDuration())
				.distanceKm(bus.getDistanceKm()).baseFare(java.math.BigDecimal.valueOf(bus.getDistanceKm() * 1.5))
				.availableSeats(40).amenities(List.of("AC", "Charging", "WiFi")).status("ON_TIME").build();
	}

	private SearchResponse mapMetroToResponse(Metro metro) {
		return SearchResponse.builder().transportId(metro.getId()).name(metro.getName()).number(metro.getNumber())
				.type("METRO").source(metro.getSource()).destination(metro.getDestination())
				.departureTime(metro.getDepartureTime()).arrivalTime(metro.getArrivalTime())
				.duration(metro.getDuration()).distanceKm(metro.getDistanceKm())
				.baseFare(java.math.BigDecimal.valueOf(metro.getDistanceKm() * 0.5)).availableSeats(100)
				.amenities(List.of("AC", "WiFi")).status("ON_TIME").build();
	}
}