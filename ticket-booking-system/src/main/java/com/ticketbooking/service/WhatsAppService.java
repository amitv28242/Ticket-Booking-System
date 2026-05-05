package com.ticketbooking.service;

import com.ticketbooking.entity.Booking;

public interface WhatsAppService {
    void sendTicketConfirmation(Booking booking);
    void sendBookingCancellation(Booking booking);
    void sendOtp(String mobileNumber, String otp);
    void sendPaymentConfirmation(String mobileNumber, String message);
}