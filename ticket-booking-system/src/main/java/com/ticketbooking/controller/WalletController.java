package com.ticketbooking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ticketbooking.dto.AddMoneyRequest;
import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.WalletDTO;
import com.ticketbooking.dto.WalletTransactionDTO;
import com.ticketbooking.security.UserPrincipal;
import com.ticketbooking.service.WalletService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    // Manual Constructor
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<WalletDTO>> getWallet(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        return ResponseEntity.ok(
                walletService.getWallet(userPrincipal.getId())
        );
    }

    @PostMapping("/add-money")
    public ResponseEntity<ApiResponse<WalletDTO>> addMoney(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody AddMoneyRequest request) {

        return ResponseEntity.ok(
                walletService.addMoney(userPrincipal.getId(), request)
        );
    }

    @GetMapping("/transactions")
    public ResponseEntity<ApiResponse<List<WalletTransactionDTO>>> getTransactions(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        return ResponseEntity.ok(
                walletService.getTransactions(userPrincipal.getId())
        );
    }
}