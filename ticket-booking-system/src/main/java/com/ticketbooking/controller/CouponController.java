
package com.ticketbooking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.ApplyCouponRequest;
import com.ticketbooking.dto.CouponDTO;
import com.ticketbooking.service.CouponService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;

    // ✅ MANUAL CONSTRUCTOR
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<CouponDTO>> applyCoupon(
            @Valid @RequestBody ApplyCouponRequest request) {
        return ResponseEntity.ok(couponService.applyCoupon(request));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CouponDTO>>> getActiveCoupons() {
        return ResponseEntity.ok(couponService.getActiveCoupons());
    }

    @GetMapping("/validate")
    public ResponseEntity<ApiResponse<CouponDTO>> validateCoupon(
            @RequestParam String code,
            @RequestParam String transportType) {
        return ResponseEntity.ok(couponService.validateCoupon(code, transportType));
    }
}