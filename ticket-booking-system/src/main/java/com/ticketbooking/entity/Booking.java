package com.ticketbooking.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ticketbooking.dto.BookingResponse.BookingResponseBuilder;
import com.ticketbooking.enums.BookingStatus;
import com.ticketbooking.enums.TicketType;
import com.ticketbooking.enums.TransportType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String pnrNumber;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id", nullable = false)
    private Transport transport;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransportType transportType;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketType ticketType;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private BookingStatus status = BookingStatus.PENDING;
    
    @Column(nullable = false)
    private LocalDate journeyDate;
    
    private LocalDate returnDate;
    
    @Column(nullable = false)
    private String source;
    
    @Column(nullable = false)
    private String destination;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalFare;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal baseFare;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal discountAmount;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal taxAmount;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal convenienceFee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon appliedCoupon;
    
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<BookingPassenger> bookingPassengers = new ArrayList<>();
    
    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;
    
    @Column(nullable = false)
    @Builder.Default
    private Integer totalPassengers = 0;
    
    private String boardingPoint;
    private String droppingPoint;
    private String specialInstructions;
    
    @CreationTimestamp
    private LocalDateTime bookingTime;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    private LocalDateTime cancellationTime;
    private String cancellationReason;
    private BigDecimal refundAmount;
    
    private Boolean emailSent = false;
    private Boolean whatsappSent = false;
    private String ticketPdfUrl;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
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
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
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
	public BigDecimal getBaseFare() {
		return baseFare;
	}
	public void setBaseFare(BigDecimal baseFare) {
		this.baseFare = baseFare;
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
	public BigDecimal getConvenienceFee() {
		return convenienceFee;
	}
	public void setConvenienceFee(BigDecimal convenienceFee) {
		this.convenienceFee = convenienceFee;
	}
	public Coupon getAppliedCoupon() {
		return appliedCoupon;
	}
	public void setAppliedCoupon(Coupon appliedCoupon) {
		this.appliedCoupon = appliedCoupon;
	}
	public List<BookingPassenger> getBookingPassengers() {
		return bookingPassengers;
	}
	public void setBookingPassengers(List<BookingPassenger> bookingPassengers) {
		this.bookingPassengers = bookingPassengers;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Integer getTotalPassengers() {
		return totalPassengers;
	}
	public void setTotalPassengers(Integer totalPassengers) {
		this.totalPassengers = totalPassengers;
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
	public LocalDateTime getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public LocalDateTime getCancellationTime() {
		return cancellationTime;
	}
	public void setCancellationTime(LocalDateTime cancellationTime) {
		this.cancellationTime = cancellationTime;
	}
	public String getCancellationReason() {
		return cancellationReason;
	}
	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}
	public BigDecimal getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}
	public Boolean getEmailSent() {
		return emailSent;
	}
	public void setEmailSent(Boolean emailSent) {
		this.emailSent = emailSent;
	}
	public Boolean getWhatsappSent() {
		return whatsappSent;
	}
	public void setWhatsappSent(Boolean whatsappSent) {
		this.whatsappSent = whatsappSent;
	}
	public String getTicketPdfUrl() {
		return ticketPdfUrl;
	}
	public void setTicketPdfUrl(String ticketPdfUrl) {
		this.ticketPdfUrl = ticketPdfUrl;
	}
	
	// Manual Builder Method
	public static BookingBuilder builder() {
	    return new BookingBuilder();
	}

	// Inner Builder Class
	public static class BookingBuilder {

	    private Long id;
	    private String pnrNumber;
	    private User user;
	    private Transport transport;
	    private TransportType transportType;
	    private TicketType ticketType;
	    private BookingStatus status = BookingStatus.PENDING;
	    private LocalDate journeyDate;
	    private LocalDate returnDate;
	    private String source;
	    private String destination;
	    private BigDecimal totalFare;
	    private BigDecimal baseFare;
	    private BigDecimal discountAmount;
	    private BigDecimal taxAmount;
	    private BigDecimal convenienceFee;
	    private Coupon appliedCoupon;
	    private List<BookingPassenger> bookingPassengers = new ArrayList<>();
	    private Payment payment;
	    private Integer totalPassengers = 0;
	    private String boardingPoint;
	    private String droppingPoint;
	    private String specialInstructions;
	    private LocalDateTime bookingTime;
	    private LocalDateTime updatedAt;
	    private LocalDateTime cancellationTime;
	    private String cancellationReason;
	    private BigDecimal refundAmount;
	    private Boolean emailSent = false;
	    private Boolean whatsappSent = false;
	    private String ticketPdfUrl;

	    public BookingBuilder id(Long id) {
	        this.id = id;
	        return this;
	    }

	    public BookingBuilder pnrNumber(String pnrNumber) {
	        this.pnrNumber = pnrNumber;
	        return this;
	    }

	    public BookingBuilder user(User user) {
	        this.user = user;
	        return this;
	    }

	    public BookingBuilder transport(Transport transport) {
	        this.transport = transport;
	        return this;
	    }

	    public BookingBuilder transportType(TransportType transportType) {
	        this.transportType = transportType;
	        return this;
	    }

	    public BookingBuilder ticketType(TicketType ticketType) {
	        this.ticketType = ticketType;
	        return this;
	    }

	    public BookingBuilder status(BookingStatus status) {
	        this.status = status;
	        return this;
	    }

	    public BookingBuilder journeyDate(LocalDate journeyDate) {
	        this.journeyDate = journeyDate;
	        return this;
	    }

	    public BookingBuilder returnDate(LocalDate returnDate) {
	        this.returnDate = returnDate;
	        return this;
	    }

	    public BookingBuilder source(String source) {
	        this.source = source;
	        return this;
	    }

	    public BookingBuilder destination(String destination) {
	        this.destination = destination;
	        return this;
	    }

	    public BookingBuilder totalFare(BigDecimal totalFare) {
	        this.totalFare = totalFare;
	        return this;
	    }

	    public BookingBuilder baseFare(BigDecimal baseFare) {
	        this.baseFare = baseFare;
	        return this;
	    }

	    public BookingBuilder discountAmount(BigDecimal discountAmount) {
	        this.discountAmount = discountAmount;
	        return this;
	    }

	    public BookingBuilder taxAmount(BigDecimal taxAmount) {
	        this.taxAmount = taxAmount;
	        return this;
	    }

	    public BookingBuilder convenienceFee(BigDecimal convenienceFee) {
	        this.convenienceFee = convenienceFee;
	        return this;
	    }

	    public BookingBuilder appliedCoupon(Coupon appliedCoupon) {
	        this.appliedCoupon = appliedCoupon;
	        return this;
	    }

	    public BookingBuilder bookingPassengers(List<BookingPassenger> bookingPassengers) {
	        this.bookingPassengers = bookingPassengers;
	        return this;
	    }

	    public BookingBuilder payment(Payment payment) {
	        this.payment = payment;
	        return this;
	    }

	    public BookingBuilder totalPassengers(Integer totalPassengers) {
	        this.totalPassengers = totalPassengers;
	        return this;
	    }

	    public BookingBuilder boardingPoint(String boardingPoint) {
	        this.boardingPoint = boardingPoint;
	        return this;
	    }

	    public BookingBuilder droppingPoint(String droppingPoint) {
	        this.droppingPoint = droppingPoint;
	        return this;
	    }

	    public BookingBuilder specialInstructions(String specialInstructions) {
	        this.specialInstructions = specialInstructions;
	        return this;
	    }

	    public BookingBuilder bookingTime(LocalDateTime bookingTime) {
	        this.bookingTime = bookingTime;
	        return this;
	    }

	    public BookingBuilder updatedAt(LocalDateTime updatedAt) {
	        this.updatedAt = updatedAt;
	        return this;
	    }

	    public BookingBuilder cancellationTime(LocalDateTime cancellationTime) {
	        this.cancellationTime = cancellationTime;
	        return this;
	    }

	    public BookingBuilder cancellationReason(String cancellationReason) {
	        this.cancellationReason = cancellationReason;
	        return this;
	    }

	    public BookingBuilder refundAmount(BigDecimal refundAmount) {
	        this.refundAmount = refundAmount;
	        return this;
	    }

	    public BookingBuilder emailSent(Boolean emailSent) {
	        this.emailSent = emailSent;
	        return this;
	    }

	    public BookingBuilder whatsappSent(Boolean whatsappSent) {
	        this.whatsappSent = whatsappSent;
	        return this;
	    }

	    public BookingBuilder ticketPdfUrl(String ticketPdfUrl) {
	        this.ticketPdfUrl = ticketPdfUrl;
	        return this;
	    }

	    public Booking build() {

	        Booking booking = new Booking();

	        booking.id = this.id;
	        booking.pnrNumber = this.pnrNumber;
	        booking.user = this.user;
	        booking.transport = this.transport;
	        booking.transportType = this.transportType;
	        booking.ticketType = this.ticketType;
	        booking.status = this.status;
	        booking.journeyDate = this.journeyDate;
	        booking.returnDate = this.returnDate;
	        booking.source = this.source;
	        booking.destination = this.destination;
	        booking.totalFare = this.totalFare;
	        booking.baseFare = this.baseFare;
	        booking.discountAmount = this.discountAmount;
	        booking.taxAmount = this.taxAmount;
	        booking.convenienceFee = this.convenienceFee;
	        booking.appliedCoupon = this.appliedCoupon;
	        booking.bookingPassengers = this.bookingPassengers;
	        booking.payment = this.payment;
	        booking.totalPassengers = this.totalPassengers;
	        booking.boardingPoint = this.boardingPoint;
	        booking.droppingPoint = this.droppingPoint;
	        booking.specialInstructions = this.specialInstructions;
	        booking.bookingTime = this.bookingTime;
	        booking.updatedAt = this.updatedAt;
	        booking.cancellationTime = this.cancellationTime;
	        booking.cancellationReason = this.cancellationReason;
	        booking.refundAmount = this.refundAmount;
	        booking.emailSent = this.emailSent;
	        booking.whatsappSent = this.whatsappSent;
	        booking.ticketPdfUrl = this.ticketPdfUrl;

	        return booking;
	    }
	}
	
}