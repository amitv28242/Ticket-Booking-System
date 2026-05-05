package com.ticketbooking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ticketbooking.enums.TransportType;

public class RunningStatusDTO {

    private Long id;
    private String transportNumber;
    private TransportType transportType;
    private String currentStation;
    private String nextStation;
    private String status;
    private Integer delayMinutes;
    private String delayReason;
    private LocalDate journeyDate;
    private String platformNumber;
    private String expectedArrival;
    private String expectedDeparture;
    private LocalDateTime lastUpdated;

    // Default Constructor
    public RunningStatusDTO() {
    }

    // All Args Constructor
    public RunningStatusDTO(
            Long id,
            String transportNumber,
            TransportType transportType,
            String currentStation,
            String nextStation,
            String status,
            Integer delayMinutes,
            String delayReason,
            LocalDate journeyDate,
            String platformNumber,
            String expectedArrival,
            String expectedDeparture,
            LocalDateTime lastUpdated) {

        this.id = id;
        this.transportNumber = transportNumber;
        this.transportType = transportType;
        this.currentStation = currentStation;
        this.nextStation = nextStation;
        this.status = status;
        this.delayMinutes = delayMinutes;
        this.delayReason = delayReason;
        this.journeyDate = journeyDate;
        this.platformNumber = platformNumber;
        this.expectedArrival = expectedArrival;
        this.expectedDeparture = expectedDeparture;
        this.lastUpdated = lastUpdated;
    }

    // Manual Builder Method
    public static RunningStatusDTOBuilder builder() {
        return new RunningStatusDTOBuilder();
    }

    // Inner Builder Class
    public static class RunningStatusDTOBuilder {

        private Long id;
        private String transportNumber;
        private TransportType transportType;
        private String currentStation;
        private String nextStation;
        private String status;
        private Integer delayMinutes;
        private String delayReason;
        private LocalDate journeyDate;
        private String platformNumber;
        private String expectedArrival;
        private String expectedDeparture;
        private LocalDateTime lastUpdated;

        public RunningStatusDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RunningStatusDTOBuilder transportNumber(String transportNumber) {
            this.transportNumber = transportNumber;
            return this;
        }

        public RunningStatusDTOBuilder transportType(TransportType transportType) {
            this.transportType = transportType;
            return this;
        }

        public RunningStatusDTOBuilder currentStation(String currentStation) {
            this.currentStation = currentStation;
            return this;
        }

        public RunningStatusDTOBuilder nextStation(String nextStation) {
            this.nextStation = nextStation;
            return this;
        }

        public RunningStatusDTOBuilder status(String status) {
            this.status = status;
            return this;
        }

        public RunningStatusDTOBuilder delayMinutes(Integer delayMinutes) {
            this.delayMinutes = delayMinutes;
            return this;
        }

        public RunningStatusDTOBuilder delayReason(String delayReason) {
            this.delayReason = delayReason;
            return this;
        }

        public RunningStatusDTOBuilder journeyDate(LocalDate journeyDate) {
            this.journeyDate = journeyDate;
            return this;
        }

        public RunningStatusDTOBuilder platformNumber(String platformNumber) {
            this.platformNumber = platformNumber;
            return this;
        }

        public RunningStatusDTOBuilder expectedArrival(String expectedArrival) {
            this.expectedArrival = expectedArrival;
            return this;
        }

        public RunningStatusDTOBuilder expectedDeparture(String expectedDeparture) {
            this.expectedDeparture = expectedDeparture;
            return this;
        }

        public RunningStatusDTOBuilder lastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public RunningStatusDTO build() {

            RunningStatusDTO dto = new RunningStatusDTO();

            dto.id = this.id;
            dto.transportNumber = this.transportNumber;
            dto.transportType = this.transportType;
            dto.currentStation = this.currentStation;
            dto.nextStation = this.nextStation;
            dto.status = this.status;
            dto.delayMinutes = this.delayMinutes;
            dto.delayReason = this.delayReason;
            dto.journeyDate = this.journeyDate;
            dto.platformNumber = this.platformNumber;
            dto.expectedArrival = this.expectedArrival;
            dto.expectedDeparture = this.expectedDeparture;
            dto.lastUpdated = this.lastUpdated;

            return dto;
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(String currentStation) {
        this.currentStation = currentStation;
    }

    public String getNextStation() {
        return nextStation;
    }

    public void setNextStation(String nextStation) {
        this.nextStation = nextStation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(Integer delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    public String getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }

    public String getPlatformNumber() {
        return platformNumber;
    }

    public void setPlatformNumber(String platformNumber) {
        this.platformNumber = platformNumber;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    public void setExpectedArrival(String expectedArrival) {
        this.expectedArrival = expectedArrival;
    }

    public String getExpectedDeparture() {
        return expectedDeparture;
    }

    public void setExpectedDeparture(String expectedDeparture) {
        this.expectedDeparture = expectedDeparture;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}