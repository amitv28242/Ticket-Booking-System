package com.ticketbooking.service.impl;

import com.ticketbooking.entity.Booking;
import com.ticketbooking.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {
    
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    public EmailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }
    
    @Override
    public void sendTicketConfirmationEmail(Booking booking, String pdfPath) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setFrom(fromEmail);
            helper.setTo(booking.getUser().getEmail());
            helper.setSubject("Ticket Booking Confirmation - PNR: " + booking.getPnrNumber());
            
            Context context = new Context();
            context.setVariable("booking", booking);
            context.setVariable("passengers", booking.getBookingPassengers());
            
            String htmlContent = templateEngine.process("ticket-confirmation", context);
            helper.setText(htmlContent, true);
            
            FileSystemResource file = new FileSystemResource(new File(pdfPath));
            helper.addAttachment("Ticket_" + booking.getPnrNumber() + ".pdf", file);
            
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void sendBookingCancellationEmail(Booking booking) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(booking.getUser().getEmail());
        message.setSubject("Booking Cancellation - PNR: " + booking.getPnrNumber());
        message.setText(String.format(
            "Dear %s,\n\nYour booking with PNR %s has been cancelled.\nRefund Amount: Rs. %s\nReason: %s\n\nThank you.",
            booking.getUser().getFirstName(),
            booking.getPnrNumber(),
            booking.getRefundAmount(),
            booking.getCancellationReason()
        ));
        mailSender.send(message);
    }
    
    @Override
    public void sendPasswordResetEmail(String email, String resetToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("Click the link to reset your password: http://localhost:3000/reset-password?token=" + resetToken);
        mailSender.send(message);
    }
    
    @Override
    public void sendEmailVerification(String email, String verificationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("Email Verification");
        message.setText("Click the link to verify your email: http://localhost:3000/verify-email?token=" + verificationToken);
        mailSender.send(message);
    }
    
    @Override
    public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp + "\nValid for 5 minutes.");
        mailSender.send(message);
    }
}