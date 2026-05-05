package com.ticketbooking.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.ApplyCouponRequest;
import com.ticketbooking.dto.CouponDTO;
import com.ticketbooking.entity.Coupon;
import com.ticketbooking.exception.ResourceNotFoundException;
import com.ticketbooking.exception.ValidationException;
import com.ticketbooking.repository.CouponRepository;
import com.ticketbooking.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {
    
    private final CouponRepository couponRepository;
    
    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
    
    @Override
    public ApiResponse<CouponDTO> applyCoupon(ApplyCouponRequest request) {
        Coupon coupon = couponRepository.findByCode(request.getCode())
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
        
        validateCoupon(coupon, request.getBookingAmount(), request.getTransportType());
        
        BigDecimal discount = calculateDiscount(coupon, request.getBookingAmount());
        
        coupon.setUsedCount(coupon.getUsedCount() + 1);
        couponRepository.save(coupon);
        
        return ApiResponse.success(mapToDTO(coupon), "Coupon applied successfully. Discount: Rs. " + discount);
    }
    
    @Override
    public ApiResponse<List<CouponDTO>> getActiveCoupons() {
        List<CouponDTO> coupons = couponRepository.findValidCoupons(LocalDate.now())
                .stream().map(this::mapToDTO).collect(Collectors.toList());
        return ApiResponse.success(coupons, "Active coupons retrieved");
    }
    
    @Override
    public ApiResponse<CouponDTO> validateCoupon(String code, String transportType) {
        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));
        
        if (!coupon.getIsActive()) {
            throw new ValidationException("Coupon is inactive");
        }
        
        if (coupon.getValidFrom().isAfter(LocalDate.now()) || coupon.getValidUntil().isBefore(LocalDate.now())) {
            throw new ValidationException("Coupon is expired");
        }
        
        if (coupon.getUsedCount() >= coupon.getMaxUsage()) {
            throw new ValidationException("Coupon usage limit exceeded");
        }
        
        if (!"ALL".equals(coupon.getApplicableTransportType()) && 
            !coupon.getApplicableTransportType().equals(transportType)) {
            throw new ValidationException("Coupon not applicable for this transport type");
        }
        
        return ApiResponse.success(mapToDTO(coupon), "Coupon is valid");
    }
    
    private void validateCoupon(Coupon coupon, BigDecimal bookingAmount, String transportType) {
        if (!coupon.getIsActive()) {
            throw new ValidationException("Coupon is inactive");
        }
        
        if (coupon.getValidFrom().isAfter(LocalDate.now()) || coupon.getValidUntil().isBefore(LocalDate.now())) {
            throw new ValidationException("Coupon is expired");
        }
        
        if (coupon.getUsedCount() >= coupon.getMaxUsage()) {
            throw new ValidationException("Coupon usage limit exceeded");
        }
        
        if (bookingAmount.compareTo(coupon.getMinBookingAmount()) < 0) {
            throw new ValidationException("Minimum booking amount should be Rs. " + coupon.getMinBookingAmount());
        }
        
        if (!"ALL".equals(coupon.getApplicableTransportType()) && 
            !coupon.getApplicableTransportType().equals(transportType)) {
            throw new ValidationException("Coupon not applicable for this transport type");
        }
    }
    
    private BigDecimal calculateDiscount(Coupon coupon, BigDecimal amount) {
        if ("PERCENTAGE".equals(coupon.getDiscountType())) {
            BigDecimal discount = amount.multiply(coupon.getDiscountValue().divide(BigDecimal.valueOf(100)));
            return discount.min(coupon.getMaxDiscount());
        } else {
            return coupon.getDiscountValue().min(amount);
        }
    }
    
    private CouponDTO mapToDTO(Coupon coupon) {
        return CouponDTO.builder()
                .id(coupon.getId())
                .code(coupon.getCode())
                .description(coupon.getDescription())
                .discountType(coupon.getDiscountType())
                .discountValue(coupon.getDiscountValue())
                .maxDiscount(coupon.getMaxDiscount())
                .minBookingAmount(coupon.getMinBookingAmount())
                .validFrom(coupon.getValidFrom())
                .validUntil(coupon.getValidUntil())
                .isActive(coupon.getIsActive())
                .applicableTransportType(coupon.getApplicableTransportType())
                .build();
    }
}