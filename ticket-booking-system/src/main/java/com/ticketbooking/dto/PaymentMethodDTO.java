package com.ticketbooking.dto;

import com.ticketbooking.enums.PaymentMethod;

public class PaymentMethodDTO {

    private Long id;
    private PaymentMethod paymentMethod;
    private String provider;
    private String accountNumber;
    private String expiryDate;
    private String cardHolderName;
    private String upiId;
    private Boolean isDefault;
    private Boolean isActive;

    // Default Constructor
    public PaymentMethodDTO() {
    }

    // All Args Constructor
    public PaymentMethodDTO(
            Long id,
            PaymentMethod paymentMethod,
            String provider,
            String accountNumber,
            String expiryDate,
            String cardHolderName,
            String upiId,
            Boolean isDefault,
            Boolean isActive) {

        this.id = id;
        this.paymentMethod = paymentMethod;
        this.provider = provider;
        this.accountNumber = accountNumber;
        this.expiryDate = expiryDate;
        this.cardHolderName = cardHolderName;
        this.upiId = upiId;
        this.isDefault = isDefault;
        this.isActive = isActive;
    }

    // Manual Builder Method
    public static PaymentMethodDTOBuilder builder() {
        return new PaymentMethodDTOBuilder();
    }

    // Inner Builder Class
    public static class PaymentMethodDTOBuilder {

        private Long id;
        private PaymentMethod paymentMethod;
        private String provider;
        private String accountNumber;
        private String expiryDate;
        private String cardHolderName;
        private String upiId;
        private Boolean isDefault;
        private Boolean isActive;

        public PaymentMethodDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PaymentMethodDTOBuilder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public PaymentMethodDTOBuilder provider(String provider) {
            this.provider = provider;
            return this;
        }

        public PaymentMethodDTOBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public PaymentMethodDTOBuilder expiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public PaymentMethodDTOBuilder cardHolderName(String cardHolderName) {
            this.cardHolderName = cardHolderName;
            return this;
        }

        public PaymentMethodDTOBuilder upiId(String upiId) {
            this.upiId = upiId;
            return this;
        }

        public PaymentMethodDTOBuilder isDefault(Boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public PaymentMethodDTOBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public PaymentMethodDTO build() {

            PaymentMethodDTO dto = new PaymentMethodDTO();

            dto.id = this.id;
            dto.paymentMethod = this.paymentMethod;
            dto.provider = this.provider;
            dto.accountNumber = this.accountNumber;
            dto.expiryDate = this.expiryDate;
            dto.cardHolderName = this.cardHolderName;
            dto.upiId = this.upiId;
            dto.isDefault = this.isDefault;
            dto.isActive = this.isActive;

            return dto;
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}