package com.ticketbooking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ticketbooking.dto.AddPaymentMethodRequest;
import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.PaymentMethodDTO;
import com.ticketbooking.security.UserPrincipal;
import com.ticketbooking.service.PaymentMethodService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    // Manual Constructor
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentMethodDTO>> addPaymentMethod(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody AddPaymentMethodRequest request) {

        return ResponseEntity.ok(
                paymentMethodService.addPaymentMethod(
                        userPrincipal.getId(),
                        request
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentMethodDTO>>> getPaymentMethods(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        return ResponseEntity.ok(
                paymentMethodService.getPaymentMethods(userPrincipal.getId())
        );
    }

    @DeleteMapping("/{paymentMethodId}")
    public ResponseEntity<ApiResponse<String>> deletePaymentMethod(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long paymentMethodId) {

        return ResponseEntity.ok(
                paymentMethodService.deletePaymentMethod(
                        userPrincipal.getId(),
                        paymentMethodId
                )
        );
    }

    @PutMapping("/{paymentMethodId}/set-default")
    public ResponseEntity<ApiResponse<PaymentMethodDTO>> setDefault(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long paymentMethodId) {

        return ResponseEntity.ok(
                paymentMethodService.setDefaultPaymentMethod(
                        userPrincipal.getId(),
                        paymentMethodId
                )
        );
    }
}