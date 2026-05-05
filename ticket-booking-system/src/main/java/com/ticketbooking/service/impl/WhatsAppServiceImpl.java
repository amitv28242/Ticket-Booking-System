package com.ticketbooking.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ticketbooking.entity.Booking;
import com.ticketbooking.entity.BookingPassenger;
import com.ticketbooking.service.WhatsAppService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;


@Service
public class WhatsAppServiceImpl implements WhatsAppService {
    
    @Value("${twilio.account.sid}")
    private String accountSid;
    
    @Value("${twilio.auth.token}")
    private String authToken;
    
    @Value("${twilio.whatsapp.number}")
    private String whatsappNumber;
    
    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }
    
    @Override
    public void sendTicketConfirmation(Booking booking) {
        StringBuilder message = new StringBuilder();
        message.append("Ticket Booking Confirmed!\n\n");
        message.append("PNR: ").append(booking.getPnrNumber()).append("\n");
        message.append("Transport: ").append(booking.getTransport().getName()).append("\n");
        message.append("Date: ").append(booking.getJourneyDate()).append("\n");
        message.append("From: ").append(booking.getSource()).append("\n");
        message.append("To: ").append(booking.getDestination()).append("\n\n");
        message.append("Passengers:\n");
        
        for (BookingPassenger bp : booking.getBookingPassengers()) {
            message.append("- ").append(bp.getPassenger().getName())
                   .append(" (Seat: ").append(bp.getSeatNumber()).append(")\n");
        }
        
        message.append("\nTotal Fare: Rs. ").append(booking.getTotalFare());
        
        sendWhatsAppMessage(booking.getUser().getMobileNumber(), message.toString());
    }
    
    @Override
    public void sendBookingCancellation(Booking booking) {
        String message = String.format(
            "Booking Cancelled\n\nPNR: %s\nRefund: Rs. %s\nStatus: Cancelled",
            booking.getPnrNumber(),
            booking.getRefundAmount()
        );
        sendWhatsAppMessage(booking.getUser().getMobileNumber(), message);
    }
    
    @Override
    public void sendOtp(String mobileNumber, String otp) {
        String message = "Your OTP is: " + otp + "\nValid for 5 minutes. Do not share it with anyone.";
        sendWhatsAppMessage(mobileNumber, message);
    }
    
    @Override
    public void sendPaymentConfirmation(String mobileNumber, String message) {
        sendWhatsAppMessage(mobileNumber, message);
    }
    
    private void sendWhatsAppMessage(String toNumber, String message) {
        try {
            String formattedNumber = toNumber.startsWith("+") ? toNumber : "+91" + toNumber;
            
            Message.creator(
                new PhoneNumber("whatsapp:" + formattedNumber),
                new PhoneNumber(whatsappNumber),
                message
            ).create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}