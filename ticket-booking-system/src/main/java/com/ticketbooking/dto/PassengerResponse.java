package com.ticketbooking.dto;

import com.ticketbooking.enums.Gender;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerResponse {
	private String name;
	private Integer age;
	private Gender gender;
	private String seatNumber;
	private String berthType;
	private String coachNumber;

	public PassengerResponse(String name, Integer age, Gender gender, String seatNumber, String berthType,
			String coachNumber) {

		this.name = name;
		this.age = age;
		this.gender = gender;
		this.seatNumber = seatNumber;
		this.berthType = berthType;
		this.coachNumber = coachNumber;
	}
	public PassengerResponse() {
		
	}

	// Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getBerthType() {
		return berthType;
	}

	public void setBerthType(String berthType) {
		this.berthType = berthType;
	}

	public String getCoachNumber() {
		return coachNumber;
	}

	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}

}