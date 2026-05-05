package com.ticketbooking.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "booking_passengers")
public class BookingPassenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(nullable = false)
    private String seatNumber;

    private String berthType;

    private String coachNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Default Constructor
    public BookingPassenger() {
    }

    // All Args Constructor
    public BookingPassenger(
            Long id,
            Booking booking,
            Passenger passenger,
            Seat seat,
            String seatNumber,
            String berthType,
            String coachNumber,
            LocalDateTime createdAt) {

        this.id = id;
        this.booking = booking;
        this.passenger = passenger;
        this.seat = seat;
        this.seatNumber = seatNumber;
        this.berthType = berthType;
        this.coachNumber = coachNumber;
        this.createdAt = createdAt;
    }

    // Manual Builder Method
    public static BookingPassengerBuilder builder() {
        return new BookingPassengerBuilder();
    }

    // Inner Builder Class
    public static class BookingPassengerBuilder {

        private Long id;
        private Booking booking;
        private Passenger passenger;
        private Seat seat;
        private String seatNumber;
        private String berthType;
        private String coachNumber;
        private LocalDateTime createdAt;

        public BookingPassengerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookingPassengerBuilder booking(Booking booking) {
            this.booking = booking;
            return this;
        }

        public BookingPassengerBuilder passenger(Passenger passenger) {
            this.passenger = passenger;
            return this;
        }

        public BookingPassengerBuilder seat(Seat seat) {
            this.seat = seat;
            return this;
        }

        public BookingPassengerBuilder seatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        public BookingPassengerBuilder berthType(String berthType) {
            this.berthType = berthType;
            return this;
        }

        public BookingPassengerBuilder coachNumber(String coachNumber) {
            this.coachNumber = coachNumber;
            return this;
        }

        public BookingPassengerBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BookingPassenger build() {
            return new BookingPassenger(
                    id,
                    booking,
                    passenger,
                    seat,
                    seatNumber,
                    berthType,
                    coachNumber,
                    createdAt
            );
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}