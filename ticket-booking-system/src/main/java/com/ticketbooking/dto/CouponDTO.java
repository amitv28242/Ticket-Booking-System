package com.ticketbooking.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponDTO {

    private Long id;
    private String code;
    private String description;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal maxDiscount;
    private BigDecimal minBookingAmount;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private Boolean isActive;
    private String applicableTransportType;

    // Default Constructor
    public CouponDTO() {
    }

    // All Args Constructor
    public CouponDTO(
            Long id,
            String code,
            String description,
            String discountType,
            BigDecimal discountValue,
            BigDecimal maxDiscount,
            BigDecimal minBookingAmount,
            LocalDate validFrom,
            LocalDate validUntil,
            Boolean isActive,
            String applicableTransportType) {

        this.id = id;
        this.code = code;
        this.description = description;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.maxDiscount = maxDiscount;
        this.minBookingAmount = minBookingAmount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.isActive = isActive;
        this.applicableTransportType = applicableTransportType;
    }

    // Manual Builder Method
    public static CouponDTOBuilder builder() {
        return new CouponDTOBuilder();
    }

    // Inner Builder Class
    public static class CouponDTOBuilder {

        private Long id;
        private String code;
        private String description;
        private String discountType;
        private BigDecimal discountValue;
        private BigDecimal maxDiscount;
        private BigDecimal minBookingAmount;
        private LocalDate validFrom;
        private LocalDate validUntil;
        private Boolean isActive;
        private String applicableTransportType;

        public CouponDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CouponDTOBuilder code(String code) {
            this.code = code;
            return this;
        }

        public CouponDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CouponDTOBuilder discountType(String discountType) {
            this.discountType = discountType;
            return this;
        }

        public CouponDTOBuilder discountValue(BigDecimal discountValue) {
            this.discountValue = discountValue;
            return this;
        }

        public CouponDTOBuilder maxDiscount(BigDecimal maxDiscount) {
            this.maxDiscount = maxDiscount;
            return this;
        }

        public CouponDTOBuilder minBookingAmount(BigDecimal minBookingAmount) {
            this.minBookingAmount = minBookingAmount;
            return this;
        }

        public CouponDTOBuilder validFrom(LocalDate validFrom) {
            this.validFrom = validFrom;
            return this;
        }

        public CouponDTOBuilder validUntil(LocalDate validUntil) {
            this.validUntil = validUntil;
            return this;
        }

        public CouponDTOBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public CouponDTOBuilder applicableTransportType(String applicableTransportType) {
            this.applicableTransportType = applicableTransportType;
            return this;
        }

        public CouponDTO build() {

            CouponDTO dto = new CouponDTO();

            dto.id = this.id;
            dto.code = this.code;
            dto.description = this.description;
            dto.discountType = this.discountType;
            dto.discountValue = this.discountValue;
            dto.maxDiscount = this.maxDiscount;
            dto.minBookingAmount = this.minBookingAmount;
            dto.validFrom = this.validFrom;
            dto.validUntil = this.validUntil;
            dto.isActive = this.isActive;
            dto.applicableTransportType = this.applicableTransportType;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(BigDecimal maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public BigDecimal getMinBookingAmount() {
        return minBookingAmount;
    }

    public void setMinBookingAmount(BigDecimal minBookingAmount) {
        this.minBookingAmount = minBookingAmount;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getApplicableTransportType() {
        return applicableTransportType;
    }

    public void setApplicableTransportType(String applicableTransportType) {
        this.applicableTransportType = applicableTransportType;
    }
}