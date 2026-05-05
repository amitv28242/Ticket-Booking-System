package com.ticketbooking.service;

import com.ticketbooking.entity.Booking;

public interface EmailService {
    void sendTicketConfirmationEmail(Booking booking, String pdfPath);
    void sendBookingCancellationEmail(Booking booking);
    void sendPasswordResetEmail(String email, String resetToken);
    void sendEmailVerification(String email, String verificationToken);
    void sendOtpEmail(String email, String otp);
}