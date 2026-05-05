package com.ticketbooking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WalletTransactionDTO {

    private Long id;
    private BigDecimal amount;
    private String type;
    private String description;
    private String status;
    private String referenceId;
    private LocalDateTime createdAt;

    // Default Constructor
    public WalletTransactionDTO() {
    }

    // All Args Constructor
    public WalletTransactionDTO(
            Long id,
            BigDecimal amount,
            String type,
            String description,
            String status,
            String referenceId,
            LocalDateTime createdAt) {

        this.id = id;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.status = status;
        this.referenceId = referenceId;
        this.createdAt = createdAt;
    }

    // Manual Builder Method
    public static WalletTransactionDTOBuilder builder() {
        return new WalletTransactionDTOBuilder();
    }

    // Inner Builder Class
    public static class WalletTransactionDTOBuilder {

        private Long id;
        private BigDecimal amount;
        private String type;
        private String description;
        private String status;
        private String referenceId;
        private LocalDateTime createdAt;

        public WalletTransactionDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WalletTransactionDTOBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public WalletTransactionDTOBuilder type(String type) {
            this.type = type;
            return this;
        }

        public WalletTransactionDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public WalletTransactionDTOBuilder status(String status) {
            this.status = status;
            return this;
        }

        public WalletTransactionDTOBuilder referenceId(String referenceId) {
            this.referenceId = referenceId;
            return this;
        }

        public WalletTransactionDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public WalletTransactionDTO build() {

            WalletTransactionDTO dto = new WalletTransactionDTO();

            dto.id = this.id;
            dto.amount = this.amount;
            dto.type = this.type;
            dto.description = this.description;
            dto.status = this.status;
            dto.referenceId = this.referenceId;
            dto.createdAt = this.createdAt;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}