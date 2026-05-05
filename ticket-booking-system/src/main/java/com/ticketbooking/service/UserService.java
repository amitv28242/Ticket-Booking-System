package com.ticketbooking.service;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.UserDTO;

public interface UserService {
    ApiResponse<UserDTO> getCurrentUser(Long userId);
    ApiResponse<UserDTO> updateProfile(Long userId, UserDTO userDTO);
    ApiResponse<String> changePassword(Long userId, String oldPassword, String newPassword);
    ApiResponse<String> uploadProfileImage(Long userId, byte[] imageData);
}