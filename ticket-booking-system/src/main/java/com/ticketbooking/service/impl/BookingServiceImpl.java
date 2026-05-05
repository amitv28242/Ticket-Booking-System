package com.ticketbooking.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.BookingRequest;
import com.ticketbooking.dto.BookingResponse;
import com.ticketbooking.dto.PassengerRequest;
import com.ticketbooking.dto.PassengerResponse;
import com.ticketbooking.entity.Booking;
import com.ticketbooking.entity.BookingPassenger;
import com.ticketbooking.entity.Coupon;
import com.ticketbooking.entity.Passenger;
import com.ticketbooking.entity.Payment;
import com.ticketbooking.entity.Schedule;
import com.ticketbooking.entity.Seat;
import com.ticketbooking.entity.Transport;
import com.ticketbooking.entity.User;
import com.ticketbooking.enums.BookingStatus;
import com.ticketbooking.enums.PaymentMethod;
import com.ticketbooking.enums.PaymentStatus;
import com.ticketbooking.exception.BookingException;
import com.ticketbooking.exception.ResourceNotFoundException;
import com.ticketbooking.exception.UnauthorizedException;
import com.ticketbooking.exception.ValidationException;
import com.ticketbooking.repository.BookingPassengerRepository;
import com.ticketbooking.repository.BookingRepository;
import com.ticketbooking.repository.CouponRepository;
import com.ticketbooking.repository.PassengerRepository;
import com.ticketbooking.repository.PaymentRepository;
import com.ticketbooking.repository.ScheduleRepository;
import com.ticketbooking.repository.SeatRepository;
import com.ticketbooking.repository.TransportRepository;
import com.ticketbooking.repository.UserRepository;
import com.ticketbooking.service.BookingService;
import com.ticketbooking.service.EmailService;
import com.ticketbooking.service.PdfService;
import com.ticketbooking.service.PnrGeneratorService;
import com.ticketbooking.service.WalletService;
import com.ticketbooking.service.WhatsAppService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingPassengerRepository bookingPassengerRepository;
    private final UserRepository userRepository;
    private final TransportRepository transportRepository;
    private final SeatRepository seatRepository;
    private final PassengerRepository passengerRepository;
    private final ScheduleRepository scheduleRepository;
    private final PaymentRepository paymentRepository;
    private final PnrGeneratorService pnrGeneratorService;
    private final PdfService pdfService;
    private final EmailService emailService;
    private final WhatsAppService whatsAppService;
    private final CouponRepository couponRepository;
    
    public BookingServiceImpl(
            BookingRepository bookingRepository,
            BookingPassengerRepository bookingPassengerRepository,
            UserRepository userRepository,
            TransportRepository transportRepository,
            SeatRepository seatRepository,
            PassengerRepository passengerRepository,
            ScheduleRepository scheduleRepository,
            PaymentRepository paymentRepository,
            PnrGeneratorService pnrGeneratorService,
            PdfService pdfService,
            EmailService emailService,
            WhatsAppService whatsAppService,
            CouponRepository couponRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.bookingPassengerRepository = bookingPassengerRepository;
        this.userRepository = userRepository;
        this.transportRepository = transportRepository;
        this.seatRepository = seatRepository;
        this.passengerRepository = passengerRepository;
        this.scheduleRepository = scheduleRepository;
        this.paymentRepository = paymentRepository;
        this.pnrGeneratorService = pnrGeneratorService;
        this.pdfService = pdfService;
        this.emailService = emailService;
        this.whatsAppService = whatsAppService;
        this.couponRepository = couponRepository;
    }
    
    @Override
    @Transactional
    public ApiResponse<BookingResponse> createBooking(Long userId, BookingRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Transport transport = transportRepository.findById(request.getTransportId())
                .orElseThrow(() -> new ResourceNotFoundException("Transport not found"));
        
        Schedule schedule = scheduleRepository.findByTransportIdAndJourneyDateSingle(
                transport.getId(), request.getJourneyDate())
                .orElseGet(() -> createSchedule(transport, request.getJourneyDate()));
        
        if (schedule.getAvailableSeats() < request.getPassengers().size()) {
            throw new BookingException("Not enough seats available");
        }
        
        BigDecimal baseFare = calculateBaseFare(transport, request.getPassengers().size());
        BigDecimal taxAmount = baseFare.multiply(BigDecimal.valueOf(0.18));
        BigDecimal convenienceFee = BigDecimal.valueOf(50);
        BigDecimal discountAmount = BigDecimal.ZERO;
        
        if (request.getCouponCode() != null && !request.getCouponCode().isEmpty()) {
            Coupon coupon = couponRepository.findByCode(request.getCouponCode())
                    .orElseThrow(() -> new ValidationException("Invalid coupon code"));
            discountAmount = calculateDiscount(coupon, baseFare);
        }
        
        BigDecimal totalFare = baseFare.add(taxAmount).add(convenienceFee).subtract(discountAmount);
        String pnrNumber = pnrGeneratorService.generatePnr();
        
        Booking booking = Booking.builder()
                .pnrNumber(pnrNumber)
                .user(user)
                .transport(transport)
                .transportType(request.getTransportType())
                .ticketType(request.getTicketType())
                .status(BookingStatus.CONFIRMED)
                .journeyDate(request.getJourneyDate())
                .returnDate(request.getReturnDate())
                .source(request.getSource())
                .destination(request.getDestination())
                .baseFare(baseFare)
                .taxAmount(taxAmount)
                .convenienceFee(convenienceFee)
                .discountAmount(discountAmount)
                .totalFare(totalFare)
                .totalPassengers(request.getPassengers().size())
                .boardingPoint(request.getBoardingPoint())
                .droppingPoint(request.getDroppingPoint())
                .specialInstructions(request.getSpecialInstructions())
                .build();
        
        Booking savedBooking = bookingRepository.save(booking);
        
        List<BookingPassenger> bookingPassengers = new ArrayList<>();
        List<Seat> availableSeats = seatRepository.findAvailableSeatsByTransportId(transport.getId());
        
        for (int i = 0; i < request.getPassengers().size(); i++) {
            PassengerRequest pr = request.getPassengers().get(i);
            
            Passenger passenger = Passenger.builder()
                    .user(user)
                    .name(pr.getName())
                    .age(pr.getAge())
                    .gender(pr.getGender())
                    .idProofNumber(pr.getIdProofNumber())
                    .idProofType(pr.getIdProofType())
                    .mobileNumber(pr.getMobileNumber())
                    .email(pr.getEmail())
                    .seatPreference(pr.getSeatPreference())
                    .mealPreference(pr.getMealPreference())
                    .specialAssistance(pr.getSpecialAssistance())
                    .build();
            
            Passenger savedPassenger = passengerRepository.save(passenger);
            
            Seat seat = (i < availableSeats.size()) ? availableSeats.get(i) : null;
            if (seat != null) {
                seat.setIsAvailable(false);
                seat.setIsBooked(true);
                seatRepository.save(seat);
            }
            
            BookingPassenger bp = BookingPassenger.builder()
                    .booking(savedBooking)
                    .passenger(savedPassenger)
                    .seat(seat)
                    .seatNumber(seat != null ? seat.getSeatNumber() : "N/A")
                    .berthType(seat != null ? seat.getSeatType() : "N/A")
                    .coachNumber("C1")
                    .build();
            
            bookingPassengers.add(bookingPassengerRepository.save(bp));
        }
        
        savedBooking.setBookingPassengers(bookingPassengers);
        schedule.setAvailableSeats(schedule.getAvailableSeats() - request.getPassengers().size());
        scheduleRepository.save(schedule);
        
        processPayment(savedBooking, request.getPaymentMethod());
        
        String pdfPath = pdfService.generateTicketPdf(savedBooking);
        savedBooking.setTicketPdfUrl(pdfPath);
        bookingRepository.save(savedBooking);
        
        emailService.sendTicketConfirmationEmail(savedBooking, pdfPath);
        whatsAppService.sendTicketConfirmation(savedBooking);
        
        savedBooking.setEmailSent(true);
        savedBooking.setWhatsappSent(true);
        bookingRepository.save(savedBooking);
        
        return ApiResponse.success(mapToBookingResponse(savedBooking), "Booking confirmed successfully");
    }
    
    @Override
    public ApiResponse<BookingResponse> getBookingByPnr(String pnrNumber) {
        Booking booking = bookingRepository.findByPnrNumber(pnrNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with PNR: " + pnrNumber));
        return ApiResponse.success(mapToBookingResponse(booking), "Booking found");
    }
    
    @Override
    public ApiResponse<List<BookingResponse>> getUserBookings(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserIdOrderByBookingTimeDesc(userId);
        List<BookingResponse> responses = bookings.stream()
                .map(this::mapToBookingResponse)
                .collect(Collectors.toList());
        return ApiResponse.success(responses, "User bookings retrieved");
    }
    
    @Override
    @Transactional
    public ApiResponse<BookingResponse> cancelBooking(Long userId, Long bookingId, String reason) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        
        if (!booking.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You can only cancel your own bookings");
        }
        
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new ValidationException("Booking is already cancelled");
        }
        
        if (booking.getJourneyDate().isBefore(LocalDate.now())) {
            throw new ValidationException("Cannot cancel past bookings");
        }
        
        booking.setStatus(BookingStatus.CANCELLED);
        booking.setCancellationTime(LocalDateTime.now());
        booking.setCancellationReason(reason);
        
        BigDecimal refundAmount = booking.getTotalFare().multiply(BigDecimal.valueOf(0.80));
        booking.setRefundAmount(refundAmount);
        
        for (BookingPassenger bp : booking.getBookingPassengers()) {
            if (bp.getSeat() != null) {
                Seat seat = bp.getSeat();
                seat.setIsAvailable(true);
                seat.setIsBooked(false);
                seatRepository.save(seat);
            }
        }
        
        Schedule schedule = scheduleRepository.findByTransportIdAndJourneyDateSingle(
                booking.getTransport().getId(), booking.getJourneyDate()).orElse(null);
        if (schedule != null) {
            schedule.setAvailableSeats(schedule.getAvailableSeats() + booking.getTotalPassengers());
            scheduleRepository.save(schedule);
        }
        
        Booking savedBooking = bookingRepository.save(booking);
        emailService.sendBookingCancellationEmail(savedBooking);
        whatsAppService.sendBookingCancellation(savedBooking);
        
        return ApiResponse.success(mapToBookingResponse(savedBooking), "Booking cancelled successfully");
    }
    
    @Override
    public ApiResponse<String> downloadTicket(String pnrNumber) {
        Booking booking = bookingRepository.findByPnrNumber(pnrNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return ApiResponse.success(booking.getTicketPdfUrl(), "Ticket download URL");
    }
    
    private Schedule createSchedule(Transport transport, LocalDate journeyDate) {
        Schedule schedule = Schedule.builder()
                .transport(transport)
                .journeyDate(journeyDate)
                .status("ON_TIME")
                .availableSeats(transport.getSeats().size())
                .totalSeats(transport.getSeats().size())
                .build();
        return scheduleRepository.save(schedule);
    }
    
    private BigDecimal calculateBaseFare(Transport transport, int passengerCount) {
        double basePrice = transport.getDistanceKm() * 2.5;
        return BigDecimal.valueOf(basePrice * passengerCount);
    }
    
    private BigDecimal calculateDiscount(Coupon coupon, BigDecimal baseFare) {
        if ("PERCENTAGE".equals(coupon.getDiscountType())) {
            BigDecimal discount = baseFare.multiply(coupon.getDiscountValue().divide(BigDecimal.valueOf(100)));
            return discount.min(coupon.getMaxDiscount());
        } else {
            return coupon.getDiscountValue().min(baseFare);
        }
    }
    
    private void processPayment(Booking booking, String paymentMethod) {
        Payment payment = Payment.builder()
                .booking(booking)
                .transactionId("TXN" + System.currentTimeMillis())
                .amount(booking.getTotalFare())
                .paymentMethod(PaymentMethod.valueOf(paymentMethod))
                .status(PaymentStatus.SUCCESS)
                .build();
        paymentRepository.save(payment);
    }
    
    private BookingResponse mapToBookingResponse(Booking booking) {

        List<PassengerResponse> passengers = booking.getBookingPassengers().stream()
                .map(bp -> {
                    PassengerResponse p = new PassengerResponse();
                    p.setName(bp.getPassenger().getName());
                    p.setAge(bp.getPassenger().getAge());
                    p.setGender(bp.getPassenger().getGender());
                    p.setSeatNumber(bp.getSeatNumber());
                    p.setBerthType(bp.getBerthType());
                    p.setCoachNumber(bp.getCoachNumber());
                    return p;
                })
                .collect(Collectors.toList());

        BookingResponse res = new BookingResponse();
        res.setId(booking.getId());
        res.setPnrNumber(booking.getPnrNumber());
        res.setTransportName(booking.getTransport().getName());
        res.setTransportNumber(booking.getTransport().getNumber());
        res.setTransportType(booking.getTransportType().name());
        res.setStatus(booking.getStatus());
        res.setJourneyDate(booking.getJourneyDate());
        res.setSource(booking.getSource());
        res.setDestination(booking.getDestination());
        res.setTotalFare(booking.getTotalFare());
        res.setDiscountAmount(booking.getDiscountAmount());
        res.setTaxAmount(booking.getTaxAmount());
        res.setTotalPassengers(booking.getTotalPassengers());
        res.setPassengers(passengers);
        res.setTicketPdfUrl(booking.getTicketPdfUrl());
        res.setBookingTime(booking.getBookingTime());

        return res;
    }
}