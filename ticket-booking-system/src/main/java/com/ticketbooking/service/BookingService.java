package com.ticketbooking.service;

import java.util.List;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.BookingRequest;
import com.ticketbooking.dto.BookingResponse;

public interface BookingService {
    ApiResponse<BookingResponse> createBooking(Long userId, BookingRequest request);
    ApiResponse<BookingResponse> getBookingByPnr(String pnrNumber);
    ApiResponse<List<BookingResponse>> getUserBookings(Long userId);
    ApiResponse<BookingResponse> cancelBooking(Long userId, Long bookingId, String reason);
    ApiResponse<String> downloadTicket(String pnrNumber);
}