package com.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.ticketbooking.entity.Booking.BookingBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id", nullable = false)
    private Transport transport;
    
    @Column(nullable = false)
    private LocalDate journeyDate;
    
    @Column(nullable = false)
    private String status; // ON_TIME, DELAYED, CANCELLED
    
    private String delayReason;
    private Integer delayMinutes;
    
    @Column(nullable = false)
    @Builder.Default
    private Integer availableSeats = 0;
    
    @Column(nullable = false)
    @Builder.Default
    private Integer totalSeats = 0;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

 // Manual Builder Method
    public static ScheduleBuilder builder() {
        return new ScheduleBuilder();
    }

    // Inner Builder Class
    public static class ScheduleBuilder {

        private Long id;
        private Transport transport;
        private LocalDate journeyDate;
        private String status;
        private String delayReason;
        private Integer delayMinutes;
        private Integer availableSeats = 0;
        private Integer totalSeats = 0;
        private LocalDateTime createdAt;

        public ScheduleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ScheduleBuilder transport(Transport transport) {
            this.transport = transport;
            return this;
        }

        public ScheduleBuilder journeyDate(LocalDate journeyDate) {
            this.journeyDate = journeyDate;
            return this;
        }

        public ScheduleBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ScheduleBuilder delayReason(String delayReason) {
            this.delayReason = delayReason;
            return this;
        }

        public ScheduleBuilder delayMinutes(Integer delayMinutes) {
            this.delayMinutes = delayMinutes;
            return this;
        }

        public ScheduleBuilder availableSeats(Integer availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }

        public ScheduleBuilder totalSeats(Integer totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        public ScheduleBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Schedule build() {

            Schedule schedule = new Schedule();

            schedule.id = this.id;
            schedule.transport = this.transport;
            schedule.journeyDate = this.journeyDate;
            schedule.status = this.status;
            schedule.delayReason = this.delayReason;
            schedule.delayMinutes = this.delayMinutes;
            schedule.availableSeats = this.availableSeats;
            schedule.totalSeats = this.totalSeats;
            schedule.createdAt = this.createdAt;

            return schedule;
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }

    public Integer getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(Integer delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    } 
}