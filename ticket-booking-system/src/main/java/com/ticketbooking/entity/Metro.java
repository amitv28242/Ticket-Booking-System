package com.ticketbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metros")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "transport_id")
public class Metro extends Transport {
    private String metroLine;
    private String stationCode;
    private String frequency; // Every 5 mins
    private String smartCardRequired;
    private String interchangeStation;
	public String getMetroLine() {
		return metroLine;
	}
	public void setMetroLine(String metroLine) {
		this.metroLine = metroLine;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getSmartCardRequired() {
		return smartCardRequired;
	}
	public void setSmartCardRequired(String smartCardRequired) {
		this.smartCardRequired = smartCardRequired;
	}
	public String getInterchangeStation() {
		return interchangeStation;
	}
	public void setInterchangeStation(String interchangeStation) {
		this.interchangeStation = interchangeStation;
	}
    
    
}
