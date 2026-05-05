package com.ticketbooking.service;

import java.math.BigDecimal;
import java.util.List;

import com.ticketbooking.dto.AddMoneyRequest;
import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.WalletDTO;
import com.ticketbooking.dto.WalletTransactionDTO;

public interface WalletService {
    ApiResponse<WalletDTO> getWallet(Long userId);
    ApiResponse<WalletDTO> addMoney(Long userId, AddMoneyRequest request);
    ApiResponse<WalletDTO> deductMoney(Long userId, BigDecimal amount, String description);
    ApiResponse<List<WalletTransactionDTO>> getTransactions(Long userId);
}