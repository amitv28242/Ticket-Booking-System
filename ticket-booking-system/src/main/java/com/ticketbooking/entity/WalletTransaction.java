package com.ticketbooking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wallet_transactions")
public class WalletTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wallet_id", nullable = false)
	private Wallet wallet;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String status;

	private String referenceId;

	@CreationTimestamp
	private LocalDateTime createdAt;

	// Default Constructor
	public WalletTransaction() {
	}

	// All Args Constructor
	public WalletTransaction(Long id, Wallet wallet, BigDecimal amount, String type, String description, String status,
			String referenceId, LocalDateTime createdAt) {

		this.id = id;
		this.wallet = wallet;
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.status = status;
		this.referenceId = referenceId;
		this.createdAt = createdAt;
	}

	// Builder Method
	public static WalletTransactionBuilder builder() {
		return new WalletTransactionBuilder();
	}

	// Inner Builder Class
	public static class WalletTransactionBuilder {

		private Long id;
		private Wallet wallet;
		private BigDecimal amount;
		private String type;
		private String description;
		private String status;
		private String referenceId;
		private LocalDateTime createdAt;

		public WalletTransactionBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public WalletTransactionBuilder wallet(Wallet wallet) {
			this.wallet = wallet;
			return this;
		}

		public WalletTransactionBuilder amount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public WalletTransactionBuilder type(String type) {
			this.type = type;
			return this;
		}

		public WalletTransactionBuilder description(String description) {
			this.description = description;
			return this;
		}

		public WalletTransactionBuilder status(String status) {
			this.status = status;
			return this;
		}

		public WalletTransactionBuilder referenceId(String referenceId) {
			this.referenceId = referenceId;
			return this;
		}

		public WalletTransactionBuilder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public WalletTransaction build() {

			WalletTransaction transaction = new WalletTransaction();

			transaction.id = this.id;
			transaction.wallet = this.wallet;
			transaction.amount = this.amount;
			transaction.type = this.type;
			transaction.description = this.description;
			transaction.status = this.status;
			transaction.referenceId = this.referenceId;
			transaction.createdAt = this.createdAt;

			return transaction;
		}
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
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