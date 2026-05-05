package com.ticketbooking.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketbooking.dto.AddMoneyRequest;
import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.WalletDTO;
import com.ticketbooking.dto.WalletTransactionDTO;
import com.ticketbooking.entity.Wallet;
import com.ticketbooking.entity.WalletTransaction;
import com.ticketbooking.exception.ResourceNotFoundException;
import com.ticketbooking.repository.UserRepository;
import com.ticketbooking.repository.WalletRepository;
import com.ticketbooking.repository.WalletTransactionRepository;
import com.ticketbooking.service.WalletService;

import lombok.RequiredArgsConstructor;

@Service
public class WalletServiceImpl implements WalletService {
    
    private final WalletRepository walletRepository;
    private final WalletTransactionRepository transactionRepository;
    
    public WalletServiceImpl(WalletRepository walletRepository, WalletTransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }
    
    @Override
    public ApiResponse<WalletDTO> getWallet(Long userId) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        return ApiResponse.success(mapToWalletDTO(wallet), "Wallet retrieved");
    }
    
    @Override
    @Transactional
    public ApiResponse<WalletDTO> addMoney(Long userId, AddMoneyRequest request) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        
        wallet.setBalance(wallet.getBalance().add(request.getAmount()));
        Wallet savedWallet = walletRepository.save(wallet);
        
        WalletTransaction transaction = WalletTransaction.builder()
                .wallet(savedWallet)
                .amount(request.getAmount())
                .type("CREDIT")
                .description("Money added to wallet")
                .status("SUCCESS")
                .referenceId("WAL" + System.currentTimeMillis())
                .build();
        transactionRepository.save(transaction);
        
        return ApiResponse.success(mapToWalletDTO(savedWallet), "Money added successfully");
    }
    
    @Override
    @Transactional
    public ApiResponse<WalletDTO> deductMoney(Long userId, BigDecimal amount, String description) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new com.ticketbooking.exception.ValidationException("Insufficient wallet balance");
        }
        
        wallet.setBalance(wallet.getBalance().subtract(amount));
        Wallet savedWallet = walletRepository.save(wallet);
        
        WalletTransaction transaction = WalletTransaction.builder()
                .wallet(savedWallet)
                .amount(amount)
                .type("DEBIT")
                .description(description)
                .status("SUCCESS")
                .referenceId("WAL" + System.currentTimeMillis())
                .build();
        transactionRepository.save(transaction);
        
        return ApiResponse.success(mapToWalletDTO(savedWallet), "Money deducted successfully");
    }
    
    @Override
    public ApiResponse<List<WalletTransactionDTO>> getTransactions(Long userId) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
        
        List<WalletTransactionDTO> transactions = transactionRepository.findByWalletIdOrderByCreatedAtDesc(wallet.getId())
                .stream().map(this::mapToTransactionDTO).collect(Collectors.toList());
        
        return ApiResponse.success(transactions, "Transactions retrieved");
    }
    
    private WalletDTO mapToWalletDTO(Wallet wallet) {
        List<WalletTransactionDTO> transactions = transactionRepository.findByWalletIdOrderByCreatedAtDesc(wallet.getId())
                .stream().map(this::mapToTransactionDTO).collect(Collectors.toList());
        
        return WalletDTO.builder()
                .id(wallet.getId())
                .balance(wallet.getBalance())
                .isActive(wallet.getIsActive())
                .transactions(transactions)
                .createdAt(wallet.getCreatedAt())
                .build();
    }
    
    private WalletTransactionDTO mapToTransactionDTO(WalletTransaction transaction) {
        return WalletTransactionDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .description(transaction.getDescription())
                .status(transaction.getStatus())
                .referenceId(transaction.getReferenceId())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}