package com.ticketbooking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ticketbooking.enums.PaymentMethod;
import com.ticketbooking.enums.PaymentStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false, unique = true)
    private String transactionId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    private String paymentGatewayResponse;

    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String razorpaySignature;

    @CreationTimestamp
    private LocalDateTime paymentTime;

    private LocalDateTime completedAt;

    private String failureReason;

    // Default Constructor
    public Payment() {
    }

    // All Args Constructor
    public Payment(
            Long id,
            Booking booking,
            String transactionId,
            BigDecimal amount,
            PaymentMethod paymentMethod,
            PaymentStatus status,
            String paymentGatewayResponse,
            String razorpayOrderId,
            String razorpayPaymentId,
            String razorpaySignature,
            LocalDateTime paymentTime,
            LocalDateTime completedAt,
            String failureReason) {

        this.id = id;
        this.booking = booking;
        this.transactionId = transactionId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentGatewayResponse = paymentGatewayResponse;
        this.razorpayOrderId = razorpayOrderId;
        this.razorpayPaymentId = razorpayPaymentId;
        this.razorpaySignature = razorpaySignature;
        this.paymentTime = paymentTime;
        this.completedAt = completedAt;
        this.failureReason = failureReason;
    }

    // Manual Builder Method
    public static PaymentBuilder builder() {
        return new PaymentBuilder();
    }

    // Inner Builder Class
    public static class PaymentBuilder {

        private Long id;
        private Booking booking;
        private String transactionId;
        private BigDecimal amount;
        private PaymentMethod paymentMethod;
        private PaymentStatus status = PaymentStatus.PENDING;
        private String paymentGatewayResponse;
        private String razorpayOrderId;
        private String razorpayPaymentId;
        private String razorpaySignature;
        private LocalDateTime paymentTime;
        private LocalDateTime completedAt;
        private String failureReason;

        public PaymentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PaymentBuilder booking(Booking booking) {
            this.booking = booking;
            return this;
        }

        public PaymentBuilder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public PaymentBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public PaymentBuilder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public PaymentBuilder status(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public PaymentBuilder paymentGatewayResponse(String paymentGatewayResponse) {
            this.paymentGatewayResponse = paymentGatewayResponse;
            return this;
        }

        public PaymentBuilder razorpayOrderId(String razorpayOrderId) {
            this.razorpayOrderId = razorpayOrderId;
            return this;
        }

        public PaymentBuilder razorpayPaymentId(String razorpayPaymentId) {
            this.razorpayPaymentId = razorpayPaymentId;
            return this;
        }

        public PaymentBuilder razorpaySignature(String razorpaySignature) {
            this.razorpaySignature = razorpaySignature;
            return this;
        }

        public PaymentBuilder paymentTime(LocalDateTime paymentTime) {
            this.paymentTime = paymentTime;
            return this;
        }

        public PaymentBuilder completedAt(LocalDateTime completedAt) {
            this.completedAt = completedAt;
            return this;
        }

        public PaymentBuilder failureReason(String failureReason) {
            this.failureReason = failureReason;
            return this;
        }

        public Payment build() {
            return new Payment(
                    id,
                    booking,
                    transactionId,
                    amount,
                    paymentMethod,
                    status,
                    paymentGatewayResponse,
                    razorpayOrderId,
                    razorpayPaymentId,
                    razorpaySignature,
                    paymentTime,
                    completedAt,
                    failureReason
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getPaymentGatewayResponse() {
        return paymentGatewayResponse;
    }

    public void setPaymentGatewayResponse(String paymentGatewayResponse) {
        this.paymentGatewayResponse = paymentGatewayResponse;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}