package com.ticketbooking.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.ForgotPasswordRequest;
import com.ticketbooking.dto.LoginRequest;
import com.ticketbooking.dto.LoginResponse;
import com.ticketbooking.dto.ResetPasswordRequest;
import com.ticketbooking.dto.SignupRequest;
import com.ticketbooking.dto.UserDTO;
import com.ticketbooking.entity.User;
import com.ticketbooking.entity.Wallet;
import com.ticketbooking.enums.UserRole;
import com.ticketbooking.exception.ResourceNotFoundException;
import com.ticketbooking.exception.ValidationException;
import com.ticketbooking.repository.UserRepository;
import com.ticketbooking.repository.WalletRepository;
import com.ticketbooking.security.JwtTokenProvider;
import com.ticketbooking.security.UserPrincipal;
import com.ticketbooking.service.AuthService;
import com.ticketbooking.service.EmailService;
import com.ticketbooking.service.WhatsAppService;

@Service
public class AuthServiceImpl implements AuthService {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final WhatsAppService whatsAppService;
    
    public AuthServiceImpl(UserRepository userRepository, WalletRepository walletRepository,
                           AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
                           PasswordEncoder passwordEncoder, EmailService emailService,
                           WhatsAppService whatsAppService) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.whatsAppService = whatsAppService;
    }
    
    @Override
    public ApiResponse<LoginResponse> login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = tokenProvider.generateToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(userPrincipal.getId());
        
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        UserDTO userDTO = mapToUserDTO(user);
        
        LoginResponse response = LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(86400L)
                .user(userDTO)
                .message("Login successful")
                .build();
        
        return ApiResponse.success(response, "Login successful");
    }
    
    @Override
    @Transactional
    public ApiResponse<UserDTO> signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ValidationException("Email already registered");
        }
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new ValidationException("Mobile number already registered");
        }
        
        User user = User.builder()
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .role(UserRole.USER)
                .isActive(true)
                .isEmailVerified(false)
                .isMobileVerified(false)
                .build();
        
        User savedUser = userRepository.save(user);
        
        Wallet wallet = Wallet.builder()
                .user(savedUser)
                .build();
        walletRepository.save(wallet);
        
        String verificationToken = UUID.randomUUID().toString();
        savedUser.setEmailVerificationToken(verificationToken);
        userRepository.save(savedUser);
        emailService.sendEmailVerification(savedUser.getEmail(), verificationToken);
        
        return ApiResponse.success(mapToUserDTO(savedUser), "Signup successful. Please verify your email.");
    }
    
    @Override
    public ApiResponse<String> forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.getEmail()));
        
        String resetToken = UUID.randomUUID().toString();
        user.setResetPasswordToken(resetToken);
        user.setResetPasswordExpiry(LocalDateTime.now().plusHours(24));
        userRepository.save(user);
        
        emailService.sendPasswordResetEmail(user.getEmail(), resetToken);
        return ApiResponse.success(null, "Password reset link sent to your email");
    }
    
    @Override
    @Transactional
    public ApiResponse<String> resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByResetPasswordToken(request.getToken())
                .orElseThrow(() -> new ValidationException("Invalid or expired reset token"));
        
        if (user.getResetPasswordExpiry().isBefore(LocalDateTime.now())) {
            throw new ValidationException("Reset token has expired");
        }
        
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new ValidationException("Passwords do not match");
        }
        
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setResetPasswordToken(null);
        user.setResetPasswordExpiry(null);
        userRepository.save(user);
        
        return ApiResponse.success(null, "Password reset successful");
    }
    
    @Override
    public ApiResponse<LoginResponse> refreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new ValidationException("Invalid refresh token");
        }
        Long userId = tokenProvider.getUserIdFromToken(refreshToken);
        String newAccessToken = tokenProvider.generateTokenFromUserId(userId);
        String newRefreshToken = tokenProvider.generateRefreshToken(userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        LoginResponse response = LoginResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .tokenType("Bearer")
                .expiresIn(86400L)
                .user(mapToUserDTO(user))
                .message("Token refreshed successfully")
                .build();
        
        return ApiResponse.success(response, "Token refreshed successfully");
    }
    
    @Override
    @Transactional
    public ApiResponse<String> verifyEmail(String token) {
        User user = userRepository.findByEmailVerificationToken(token)
                .orElseThrow(() -> new ValidationException("Invalid verification token"));
        
        user.setEmailVerificationToken(null);
        user.setIsEmailVerified(true);
        userRepository.save(user);
        
        return ApiResponse.success(null, "Email verified successfully");
    }
    
    @Override
    public ApiResponse<String> sendMobileOtp(String mobileNumber) {
        String otp = String.format("%06d", (int)(Math.random() * 999999));
        User user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        user.setMobileOtp(otp);
        user.setMobileOtpExpiry(LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);
        
        whatsAppService.sendOtp(mobileNumber, otp);
        return ApiResponse.success(null, "OTP sent successfully");
    }
    
    @Override
    @Transactional
    public ApiResponse<String> verifyMobileOtp(String mobileNumber, String otp) {
        User user = userRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (user.getMobileOtp() == null || !user.getMobileOtp().equals(otp)) {
            throw new ValidationException("Invalid OTP");
        }
        
        if (user.getMobileOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new ValidationException("OTP has expired");
        }
        
        user.setMobileOtp(null);
        user.setMobileOtpExpiry(null);
        user.setIsMobileVerified(true);
        userRepository.save(user);
        
        return ApiResponse.success(null, "Mobile number verified successfully");
    }
    
    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .profileImageUrl(user.getProfileImageUrl())
                .isEmailVerified(user.getIsEmailVerified())
                .isMobileVerified(user.getIsMobileVerified())
                .build();
    }
}