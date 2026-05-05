package com.ticketbooking.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ticketbooking.enums.PaymentMethod;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_methods")
public class PaymentMethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String accountNumber;

    private String expiryDate;

    private String cardHolderName;

    private String upiId;

    @Column(nullable = false)
    private Boolean isDefault = false;

    @Column(nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Default Constructor
    public PaymentMethodEntity() {
    }

    // All Args Constructor
    public PaymentMethodEntity(
            Long id,
            User user,
            PaymentMethod paymentMethod,
            String provider,
            String accountNumber,
            String expiryDate,
            String cardHolderName,
            String upiId,
            Boolean isDefault,
            Boolean isActive,
            LocalDateTime createdAt) {

        this.id = id;
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.provider = provider;
        this.accountNumber = accountNumber;
        this.expiryDate = expiryDate;
        this.cardHolderName = cardHolderName;
        this.upiId = upiId;
        this.isDefault = isDefault;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    // Manual Builder Method
    public static PaymentMethodEntityBuilder builder() {
        return new PaymentMethodEntityBuilder();
    }

    // Inner Builder Class
    public static class PaymentMethodEntityBuilder {

        private Long id;
        private User user;
        private PaymentMethod paymentMethod;
        private String provider;
        private String accountNumber;
        private String expiryDate;
        private String cardHolderName;
        private String upiId;
        private Boolean isDefault = false;
        private Boolean isActive = true;
        private LocalDateTime createdAt;

        public PaymentMethodEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PaymentMethodEntityBuilder user(User user) {
            this.user = user;
            return this;
        }

        public PaymentMethodEntityBuilder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public PaymentMethodEntityBuilder provider(String provider) {
            this.provider = provider;
            return this;
        }

        public PaymentMethodEntityBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public PaymentMethodEntityBuilder expiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public PaymentMethodEntityBuilder cardHolderName(String cardHolderName) {
            this.cardHolderName = cardHolderName;
            return this;
        }

        public PaymentMethodEntityBuilder upiId(String upiId) {
            this.upiId = upiId;
            return this;
        }

        public PaymentMethodEntityBuilder isDefault(Boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public PaymentMethodEntityBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public PaymentMethodEntityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PaymentMethodEntity build() {

            PaymentMethodEntity entity = new PaymentMethodEntity();

            entity.id = this.id;
            entity.user = this.user;
            entity.paymentMethod = this.paymentMethod;
            entity.provider = this.provider;
            entity.accountNumber = this.accountNumber;
            entity.expiryDate = this.expiryDate;
            entity.cardHolderName = this.cardHolderName;
            entity.upiId = this.upiId;
            entity.isDefault = this.isDefault;
            entity.isActive = this.isActive;
            entity.createdAt = this.createdAt;

            return entity;
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}