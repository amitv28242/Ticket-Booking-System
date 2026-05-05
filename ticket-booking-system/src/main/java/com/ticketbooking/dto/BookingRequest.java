package com.ticketbooking.dto;

import com.ticketbooking.enums.TicketType;
import com.ticketbooking.enums.TransportType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {
	@NotNull(message = "Transport ID is required")
	private Long transportId;

	@NotNull(message = "Transport type is required")
	private TransportType transportType;

	@NotNull(message = "Ticket type is required")
	private TicketType ticketType;

	@NotNull(message = "Journey date is required")
	private LocalDate journeyDate;

	private LocalDate returnDate;

	@NotEmpty(message = "At least one passenger is required")
	private List<PassengerRequest> passengers;

	@NotBlank(message = "Source is required")
	private String source;

	@NotBlank(message = "Destination is required")
	private String destination;

	private String couponCode;
	private String boardingPoint;
	private String droppingPoint;
	private String specialInstructions;
	private String paymentMethod;
	public Long getTransportId() {
		return transportId;
	}
	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}
	public TransportType getTransportType() {
		return transportType;
	}
	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}
	public TicketType getTicketType() {
		return ticketType;
	}
	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}
	public LocalDate getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public List<PassengerRequest> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerRequest> passengers) {
		this.passengers = passengers;
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
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public String getDroppingPoint() {
		return droppingPoint;
	}
	public void setDroppingPoint(String droppingPoint) {
		this.droppingPoint = droppingPoint;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}