package com.ticketbooking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketbooking.dto.AddPaymentMethodRequest;
import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.PaymentMethodDTO;
import com.ticketbooking.entity.PaymentMethodEntity;
import com.ticketbooking.entity.User;
import com.ticketbooking.exception.ResourceNotFoundException;
import com.ticketbooking.exception.UnauthorizedException;
import com.ticketbooking.repository.PaymentMethodRepository;
import com.ticketbooking.repository.UserRepository;
import com.ticketbooking.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;
    
    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository, UserRepository userRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional
    public ApiResponse<PaymentMethodDTO> addPaymentMethod(Long userId, AddPaymentMethodRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (Boolean.TRUE.equals(request.getIsDefault())) {
            paymentMethodRepository.findByUserIdAndIsDefaultTrue(userId)
                    .ifPresent(pm -> {
                        pm.setIsDefault(false);
                        paymentMethodRepository.save(pm);
                    });
        }
        
        PaymentMethodEntity paymentMethod = PaymentMethodEntity.builder()
                .user(user)
                .paymentMethod(request.getPaymentMethod())
                .provider(request.getProvider())
                .accountNumber(maskAccountNumber(request.getAccountNumber()))
                .expiryDate(request.getExpiryDate())
                .cardHolderName(request.getCardHolderName())
                .upiId(request.getUpiId())
                .isDefault(request.getIsDefault())
                .isActive(true)
                .build();
        
        PaymentMethodEntity saved = paymentMethodRepository.save(paymentMethod);
        return ApiResponse.success(mapToDTO(saved), "Payment method added successfully");
    }
    
    @Override
    public ApiResponse<List<PaymentMethodDTO>> getPaymentMethods(Long userId) {
        List<PaymentMethodDTO> methods = paymentMethodRepository.findByUserIdAndIsActiveTrue(userId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
        return ApiResponse.success(methods, "Payment methods retrieved");
    }
    
    @Override
    @Transactional
    public ApiResponse<String> deletePaymentMethod(Long userId, Long paymentMethodId) {
        PaymentMethodEntity pm = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment method not found"));
        
        if (!pm.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You can only delete your own payment methods");
        }
        
        pm.setIsActive(false);
        paymentMethodRepository.save(pm);
        return ApiResponse.success(null, "Payment method deleted successfully");
    }
    
    @Override
    @Transactional
    public ApiResponse<PaymentMethodDTO> setDefaultPaymentMethod(Long userId, Long paymentMethodId) {
        PaymentMethodEntity newDefault = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment method not found"));
        
        if (!newDefault.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Unauthorized");
        }
        
        paymentMethodRepository.findByUserIdAndIsDefaultTrue(userId)
                .ifPresent(pm -> {
                    pm.setIsDefault(false);
                    paymentMethodRepository.save(pm);
                });
        
        newDefault.setIsDefault(true);
        PaymentMethodEntity saved = paymentMethodRepository.save(newDefault);
        return ApiResponse.success(mapToDTO(saved), "Default payment method updated");
    }
    
    private String maskAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 4) return accountNumber;
        return "****" + accountNumber.substring(accountNumber.length() - 4);
    }
    
    private PaymentMethodDTO mapToDTO(PaymentMethodEntity pm) {
        return PaymentMethodDTO.builder()
                .id(pm.getId())
                .paymentMethod(pm.getPaymentMethod())
                .provider(pm.getProvider())
                .accountNumber(pm.getAccountNumber())
                .expiryDate(pm.getExpiryDate())
                .cardHolderName(pm.getCardHolderName())
                .upiId(pm.getUpiId())
                .isDefault(pm.getIsDefault())
                .isActive(pm.getIsActive())
                .build();
    }
}