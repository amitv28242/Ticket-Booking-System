package com.ticketbooking.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WalletTransaction> transactions = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Default Constructor
    public Wallet() {
    }

    // All Args Constructor
    public Wallet(
            Long id,
            User user,
            BigDecimal balance,
            Boolean isActive,
            List<WalletTransaction> transactions,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        this.id = id;
        this.user = user;
        this.balance = balance;
        this.isActive = isActive;
        this.transactions = transactions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Manual Builder Method
    public static WalletBuilder builder() {
        return new WalletBuilder();
    }

    // Inner Builder Class
    public static class WalletBuilder {

        private Long id;
        private User user;
        private BigDecimal balance = BigDecimal.ZERO;
        private Boolean isActive = true;
        private List<WalletTransaction> transactions = new ArrayList<>();
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public WalletBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WalletBuilder user(User user) {
            this.user = user;
            return this;
        }

        public WalletBuilder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public WalletBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public WalletBuilder transactions(List<WalletTransaction> transactions) {
            this.transactions = transactions;
            return this;
        }

        public WalletBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public WalletBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Wallet build() {

            Wallet wallet = new Wallet();

            wallet.id = this.id;
            wallet.user = this.user;
            wallet.balance = this.balance;
            wallet.isActive = this.isActive;
            wallet.transactions = this.transactions;
            wallet.createdAt = this.createdAt;
            wallet.updatedAt = this.updatedAt;

            return wallet;
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

    public List<WalletTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<WalletTransaction> transactions) {
        this.transactions = transactions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}