package com.ticketbooking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.BookingRequest;
import com.ticketbooking.dto.BookingResponse;
import com.ticketbooking.security.UserPrincipal;
import com.ticketbooking.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService bookingService;

	// Manual Constructor
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<BookingResponse>> createBooking(
			@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid @RequestBody BookingRequest request) {

		return ResponseEntity.ok(bookingService.createBooking(userPrincipal.getId(), request));
	}

	@GetMapping("/pnr/{pnrNumber}")
	public ResponseEntity<ApiResponse<BookingResponse>> getBookingByPnr(@PathVariable String pnrNumber) {

		return ResponseEntity.ok(bookingService.getBookingByPnr(pnrNumber));
	}

	@GetMapping("/my-bookings")
	public ResponseEntity<ApiResponse<List<BookingResponse>>> getMyBookings(
			@AuthenticationPrincipal UserPrincipal userPrincipal) {

		return ResponseEntity.ok(bookingService.getUserBookings(userPrincipal.getId()));
	}

	@PostMapping("/{bookingId}/cancel")
	public ResponseEntity<ApiResponse<BookingResponse>> cancelBooking(
			@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long bookingId,
			@RequestParam String reason) {

		return ResponseEntity.ok(bookingService.cancelBooking(userPrincipal.getId(), bookingId, reason));
	}

	@GetMapping("/{pnrNumber}/download")
	public ResponseEntity<ApiResponse<String>> downloadTicket(@PathVariable String pnrNumber) {

		return ResponseEntity.ok(bookingService.downloadTicket(pnrNumber));
	}
}