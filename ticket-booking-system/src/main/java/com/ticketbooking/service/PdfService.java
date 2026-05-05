package com.ticketbooking.service;

import com.ticketbooking.entity.Booking;

public interface PdfService {
    String generateTicketPdf(Booking booking);
}
