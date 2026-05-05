package com.ticketbooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.UserDTO;
import com.ticketbooking.security.UserPrincipal;
import com.ticketbooking.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Manual Constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserDTO>> getProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        return ResponseEntity.ok(
                userService.getCurrentUser(userPrincipal.getId())
        );
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<UserDTO>> updateProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UserDTO userDTO) {

        return ResponseEntity.ok(
                userService.updateProfile(userPrincipal.getId(), userDTO)
        );
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<String>> changePassword(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

        return ResponseEntity.ok(
                userService.changePassword(
                        userPrincipal.getId(),
                        oldPassword,
                        newPassword
                )
        );
    }
}