package com.ticketbooking.service;

import java.util.List;

import com.ticketbooking.dto.AddPaymentMethodRequest;
import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.PaymentMethodDTO;

public interface PaymentMethodService {
    ApiResponse<PaymentMethodDTO> addPaymentMethod(Long userId, AddPaymentMethodRequest request);
    ApiResponse<List<PaymentMethodDTO>> getPaymentMethods(Long userId);
    ApiResponse<String> deletePaymentMethod(Long userId, Long paymentMethodId);
    ApiResponse<PaymentMethodDTO> setDefaultPaymentMethod(Long userId, Long paymentMethodId);
}
