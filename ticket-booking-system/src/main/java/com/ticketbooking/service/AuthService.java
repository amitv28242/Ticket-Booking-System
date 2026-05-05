package com.ticketbooking.service;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.ForgotPasswordRequest;
import com.ticketbooking.dto.LoginRequest;
import com.ticketbooking.dto.LoginResponse;
import com.ticketbooking.dto.ResetPasswordRequest;
import com.ticketbooking.dto.SignupRequest;
import com.ticketbooking.dto.UserDTO;

public interface AuthService {

	ApiResponse<LoginResponse> login(LoginRequest request);

	ApiResponse<UserDTO> signup(SignupRequest request);

	ApiResponse<String> forgotPassword(ForgotPasswordRequest request);

	ApiResponse<String> resetPassword(ResetPasswordRequest request);

	ApiResponse<LoginResponse> refreshToken(String refreshToken);

	ApiResponse<String> verifyEmail(String token);

	ApiResponse<String> sendMobileOtp(String mobileNumber);

	ApiResponse<String> verifyMobileOtp(String mobileNumber, String otp);
}