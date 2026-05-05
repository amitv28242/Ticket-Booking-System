package com.ticketbooking.dto;

import com.ticketbooking.enums.BookingStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

    private Long id;
    private String pnrNumber;
    private String transportName;
    private String transportNumber;
    private String transportType;
    private BookingStatus status;
    private LocalDate journeyDate;
    private String source;
    private String destination;
    private BigDecimal totalFare;
    private BigDecimal discountAmount;
    private BigDecimal taxAmount;
    private Integer totalPassengers;
    private List<PassengerResponse> passengers;
    private String ticketPdfUrl;
    private LocalDateTime bookingTime;

    // Default Constructor
    public BookingResponse() {
    }

    // All Arguments Constructor
    public BookingResponse(
            Long id,
            String pnrNumber,
            String transportName,
            String transportNumber,
            String transportType,
            BookingStatus status,
            LocalDate journeyDate,
            String source,
            String destination,
            BigDecimal totalFare,
            BigDecimal discountAmount,
            BigDecimal taxAmount,
            Integer totalPassengers,
            List<PassengerResponse> passengers,
            String ticketPdfUrl,
            LocalDateTime bookingTime) {

        this.id = id;
        this.pnrNumber = pnrNumber;
        this.transportName = transportName;
        this.transportNumber = transportNumber;
        this.transportType = transportType;
        this.status = status;
        this.journeyDate = journeyDate;
        this.source = source;
        this.destination = destination;
        this.totalFare = totalFare;
        this.discountAmount = discountAmount;
        this.taxAmount = taxAmount;
        this.totalPassengers = totalPassengers;
        this.passengers = passengers;
        this.ticketPdfUrl = ticketPdfUrl;
        this.bookingTime = bookingTime;
    }

    // Builder Method
    public static BookingResponseBuilder builder() {
        return new BookingResponseBuilder();
    }

    // Inner Builder Class
    public static class BookingResponseBuilder {

        private Long id;
        private String pnrNumber;
        private String transportName;
        private String transportNumber;
        private String transportType;
        private BookingStatus status;
        private LocalDate journeyDate;
        private String source;
        private String destination;
        private BigDecimal totalFare;
        private BigDecimal discountAmount;
        private BigDecimal taxAmount;
        private Integer totalPassengers;
        private List<PassengerResponse> passengers;
        private String ticketPdfUrl;
        private LocalDateTime bookingTime;

        public BookingResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookingResponseBuilder pnrNumber(String pnrNumber) {
            this.pnrNumber = pnrNumber;
            return this;
        }

        public BookingResponseBuilder transportName(String transportName) {
            this.transportName = transportName;
            return this;
        }

        public BookingResponseBuilder transportNumber(String transportNumber) {
            this.transportNumber = transportNumber;
            return this;
        }

        public BookingResponseBuilder transportType(String transportType) {
            this.transportType = transportType;
            return this;
        }

        public BookingResponseBuilder status(BookingStatus status) {
            this.status = status;
            return this;
        }

        public BookingResponseBuilder journeyDate(LocalDate journeyDate) {
            this.journeyDate = journeyDate;
            return this;
        }

        public BookingResponseBuilder source(String source) {
            this.source = source;
            return this;
        }

        public BookingResponseBuilder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public BookingResponseBuilder totalFare(BigDecimal totalFare) {
            this.totalFare = totalFare;
            return this;
        }

        public BookingResponseBuilder discountAmount(BigDecimal discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }

        public BookingResponseBuilder taxAmount(BigDecimal taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public BookingResponseBuilder totalPassengers(Integer totalPassengers) {
            this.totalPassengers = totalPassengers;
            return this;
        }

        public BookingResponseBuilder passengers(List<PassengerResponse> passengers) {
            this.passengers = passengers;
            return this;
        }

        public BookingResponseBuilder ticketPdfUrl(String ticketPdfUrl) {
            this.ticketPdfUrl = ticketPdfUrl;
            return this;
        }

        public BookingResponseBuilder bookingTime(LocalDateTime bookingTime) {
            this.bookingTime = bookingTime;
            return this;
        }

        public BookingResponse build() {
            return new BookingResponse(
                    id,
                    pnrNumber,
                    transportName,
                    transportNumber,
                    transportType,
                    status,
                    journeyDate,
                    source,
                    destination,
                    totalFare,
                    discountAmount,
                    taxAmount,
                    totalPassengers,
                    passengers,
                    ticketPdfUrl,
                    bookingTime
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

    public String getPnrNumber() {
        return pnrNumber;
    }

    public void setPnrNumber(String pnrNumber) {
        this.pnrNumber = pnrNumber;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
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

    public BigDecimal getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(BigDecimal totalFare) {
        this.totalFare = totalFare;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Integer getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(Integer totalPassengers) {
        this.totalPassengers = totalPassengers;
    }

    public List<PassengerResponse> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerResponse> passengers) {
        this.passengers = passengers;
    }

    public String getTicketPdfUrl() {
        return ticketPdfUrl;
    }

    public void setTicketPdfUrl(String ticketPdfUrl) {
        this.ticketPdfUrl = ticketPdfUrl;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}