package com.ticketbooking.service;

import java.util.List;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.ApplyCouponRequest;
import com.ticketbooking.dto.CouponDTO;

public interface CouponService {
    ApiResponse<CouponDTO> applyCoupon(ApplyCouponRequest request);
    ApiResponse<List<CouponDTO>> getActiveCoupons();
    ApiResponse<CouponDTO> validateCoupon(String code, String transportType);
}