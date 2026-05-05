package com.ticketbooking.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class SearchResponse {

    private Long transportId;
    private String name;
    private String number;
    private String type;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private Double distanceKm;
    private BigDecimal baseFare;
    private Integer availableSeats;
    private List<String> amenities;
    private String status;

    // Default Constructor
    public SearchResponse() {
    }

    // All Args Constructor
    public SearchResponse(
            Long transportId,
            String name,
            String number,
            String type,
            String source,
            String destination,
            String departureTime,
            String arrivalTime,
            String duration,
            Double distanceKm,
            BigDecimal baseFare,
            Integer availableSeats,
            List<String> amenities,
            String status) {

        this.transportId = transportId;
        this.name = name;
        this.number = number;
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.distanceKm = distanceKm;
        this.baseFare = baseFare;
        this.availableSeats = availableSeats;
        this.amenities = amenities;
        this.status = status;
    }

    // Manual Builder Method
    public static SearchResponseBuilder builder() {
        return new SearchResponseBuilder();
    }

    // Inner Builder Class
    public static class SearchResponseBuilder {

        private Long transportId;
        private String name;
        private String number;
        private String type;
        private String source;
        private String destination;
        private String departureTime;
        private String arrivalTime;
        private String duration;
        private Double distanceKm;
        private BigDecimal baseFare;
        private Integer availableSeats;
        private List<String> amenities = new ArrayList<>();
        private String status;

        public SearchResponseBuilder transportId(Long transportId) {
            this.transportId = transportId;
            return this;
        }

        public SearchResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SearchResponseBuilder number(String number) {
            this.number = number;
            return this;
        }

        public SearchResponseBuilder type(String type) {
            this.type = type;
            return this;
        }

        public SearchResponseBuilder source(String source) {
            this.source = source;
            return this;
        }

        public SearchResponseBuilder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public SearchResponseBuilder departureTime(String departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public SearchResponseBuilder arrivalTime(String arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public SearchResponseBuilder duration(String duration) {
            this.duration = duration;
            return this;
        }

        public SearchResponseBuilder distanceKm(Double distanceKm) {
            this.distanceKm = distanceKm;
            return this;
        }

        public SearchResponseBuilder baseFare(BigDecimal baseFare) {
            this.baseFare = baseFare;
            return this;
        }

        public SearchResponseBuilder availableSeats(Integer availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }

        public SearchResponseBuilder amenities(List<String> amenities) {
            this.amenities = amenities;
            return this;
        }

        public SearchResponseBuilder status(String status) {
            this.status = status;
            return this;
        }

        public SearchResponse build() {

            SearchResponse response = new SearchResponse();

            response.transportId = this.transportId;
            response.name = this.name;
            response.number = this.number;
            response.type = this.type;
            response.source = this.source;
            response.destination = this.destination;
            response.departureTime = this.departureTime;
            response.arrivalTime = this.arrivalTime;
            response.duration = this.duration;
            response.distanceKm = this.distanceKm;
            response.baseFare = this.baseFare;
            response.availableSeats = this.availableSeats;
            response.amenities = this.amenities;
            response.status = this.status;

            return response;
        }
    }

    // Getters and Setters

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public BigDecimal getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(BigDecimal baseFare) {
        this.baseFare = baseFare;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}