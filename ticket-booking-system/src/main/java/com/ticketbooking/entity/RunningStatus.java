package com.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ticketbooking.enums.TransportType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "running_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RunningStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String transportNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransportType transportType;
    
    @Column(nullable = false)
    private String currentStation;
    
    private String nextStation;
    
    @Column(nullable = false)
    private String status; // RUNNING, DELAYED, ON_TIME, ARRIVED, DEPARTED
    
    private Integer delayMinutes;
    private String delayReason;
    
    @Column(nullable = false)
    private LocalDate journeyDate;
    
    private String platformNumber;
    private String expectedArrival;
    private String expectedDeparture;
    
    private Double latitude;
    private Double longitude;
    
    @CreationTimestamp
    private LocalDateTime lastUpdated;
    
    private String lastUpdatedBy;

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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
    
    
}