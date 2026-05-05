package com.ticketbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "transport_id")
public class Flight extends Transport {
    private String airline;
    private String flightNumber;
    private String aircraftType;
    private String baggageAllowance;
    private String mealIncluded;
    private String terminal;
    private String gate;
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getAircraftType() {
		return aircraftType;
	}
	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}
	public String getBaggageAllowance() {
		return baggageAllowance;
	}
	public void setBaggageAllowance(String baggageAllowance) {
		this.baggageAllowance = baggageAllowance;
	}
	public String getMealIncluded() {
		return mealIncluded;
	}
	public void setMealIncluded(String mealIncluded) {
		this.mealIncluded = mealIncluded;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getGate() {
		return gate;
	}
	public void setGate(String gate) {
		this.gate = gate;
	}
    
    
}
