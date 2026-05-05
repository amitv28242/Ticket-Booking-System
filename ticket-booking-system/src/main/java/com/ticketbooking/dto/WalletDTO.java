package com.ticketbooking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class WalletDTO {

    private Long id;
    private BigDecimal balance;
    private Boolean isActive;
    private List<WalletTransactionDTO> transactions;
    private LocalDateTime createdAt;

    // Default Constructor
    public WalletDTO() {
    }

    // All Args Constructor
    public WalletDTO(
            Long id,
            BigDecimal balance,
            Boolean isActive,
            List<WalletTransactionDTO> transactions,
            LocalDateTime createdAt) {

        this.id = id;
        this.balance = balance;
        this.isActive = isActive;
        this.transactions = transactions;
        this.createdAt = createdAt;
    }

    // Manual Builder Method
    public static WalletDTOBuilder builder() {
        return new WalletDTOBuilder();
    }

    // Inner Builder Class
    public static class WalletDTOBuilder {

        private Long id;
        private BigDecimal balance;
        private Boolean isActive;
        private List<WalletTransactionDTO> transactions;
        private LocalDateTime createdAt;

        public WalletDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WalletDTOBuilder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public WalletDTOBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public WalletDTOBuilder transactions(List<WalletTransactionDTO> transactions) {
            this.transactions = transactions;
            return this;
        }

        public WalletDTOBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public WalletDTO build() {

            WalletDTO dto = new WalletDTO();

            dto.id = this.id;
            dto.balance = this.balance;
            dto.isActive = this.isActive;
            dto.transactions = this.transactions;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<WalletTransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<WalletTransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}