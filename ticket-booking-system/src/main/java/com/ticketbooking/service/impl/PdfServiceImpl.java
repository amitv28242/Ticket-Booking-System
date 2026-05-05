package com.ticketbooking.service.impl;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.ticketbooking.entity.Booking;
import com.ticketbooking.entity.BookingPassenger;
import com.ticketbooking.service.PdfService;

@Service
public class PdfServiceImpl implements PdfService {
    
    @Value("${app.ticket.pdf.path}")
    private String pdfPath;
    
    @Override
    public String generateTicketPdf(Booking booking) {
        try {
            File directory = new File(pdfPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            String fileName = pdfPath + "TICKET_" + booking.getPnrNumber() + ".pdf";
            PdfWriter writer = new PdfWriter(new FileOutputStream(fileName));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            
            document.add(new Paragraph("TICKET BOOKING CONFIRMATION")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20)
                    .setBold());
            
            document.add(new Paragraph("\n"));
            
            Table detailsTable = new Table(UnitValue.createPercentArray(new float[]{1, 2}));
            detailsTable.addCell(new Cell().add(new Paragraph("PNR Number:").setBold()));
            detailsTable.addCell(new Cell().add(new Paragraph(booking.getPnrNumber())));
            detailsTable.addCell(new Cell().add(new Paragraph("Transport:").setBold()));
            detailsTable.addCell(new Cell().add(new Paragraph(booking.getTransport().getName() + " (" + booking.getTransport().getNumber() + ")")));
            detailsTable.addCell(new Cell().add(new Paragraph("Type:").setBold()));
            detailsTable.addCell(new Cell().add(new Paragraph(booking.getTransportType().toString())));
            detailsTable.addCell(new Cell().add(new Paragraph("Journey Date:").setBold()));
            detailsTable.addCell(new Cell().add(new Paragraph(booking.getJourneyDate().toString())));
            detailsTable.addCell(new Cell().add(new Paragraph("Source:").setBold()));
            detailsTable.addCell(new Cell().add(new Paragraph(booking.getSource())));
            detailsTable.addCell(new Cell().add(new Paragraph("Destination:").setBold()));
            detailsTable.addCell(new Cell().add(new Paragraph(booking.getDestination())));
            detailsTable.addCell(new Cell().add(new Paragraph("Status:").setBold()));
            detailsTable.addCell(new Cell().add(new Paragraph(booking.getStatus().toString())));
            document.add(detailsTable);
            
            document.add(new Paragraph("\n"));
            
            document.add(new Paragraph("PASSENGER DETAILS").setBold().setFontSize(14));
            Table passengerTable = new Table(UnitValue.createPercentArray(new float[]{1, 1, 1, 1, 1}));
            passengerTable.addHeaderCell("Name");
            passengerTable.addHeaderCell("Age");
            passengerTable.addHeaderCell("Gender");
            passengerTable.addHeaderCell("Seat");
            passengerTable.addHeaderCell("Coach");
            
            for (BookingPassenger bp : booking.getBookingPassengers()) {
                passengerTable.addCell(bp.getPassenger().getName());
                passengerTable.addCell(String.valueOf(bp.getPassenger().getAge()));
                passengerTable.addCell(bp.getPassenger().getGender().toString());
                passengerTable.addCell(bp.getSeatNumber());
                passengerTable.addCell(bp.getCoachNumber());
            }
            document.add(passengerTable);
            
            document.add(new Paragraph("\n"));
            
            document.add(new Paragraph("FARE DETAILS").setBold().setFontSize(14));
            Table fareTable = new Table(UnitValue.createPercentArray(new float[]{2, 1}));
            fareTable.addCell(new Cell().add(new Paragraph("Base Fare:")));
            fareTable.addCell(new Cell().add(new Paragraph("Rs. " + booking.getBaseFare())));
            fareTable.addCell(new Cell().add(new Paragraph("Tax (18% GST):")));
            fareTable.addCell(new Cell().add(new Paragraph("Rs. " + booking.getTaxAmount())));
            fareTable.addCell(new Cell().add(new Paragraph("Convenience Fee:")));
            fareTable.addCell(new Cell().add(new Paragraph("Rs. " + booking.getConvenienceFee())));
            if (booking.getDiscountAmount() != null && booking.getDiscountAmount().compareTo(java.math.BigDecimal.ZERO) > 0) {
                fareTable.addCell(new Cell().add(new Paragraph("Discount:")));
                fareTable.addCell(new Cell().add(new Paragraph("- Rs. " + booking.getDiscountAmount())));
            }
            fareTable.addCell(new Cell().add(new Paragraph("Total Fare:").setBold()));
            fareTable.addCell(new Cell().add(new Paragraph("Rs. " + booking.getTotalFare()).setBold()));
            document.add(fareTable);
            
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("Thank you for booking with us! Have a safe journey.")
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("For any queries, contact support@ticketbooking.com")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(10));
            
            document.close();
            return fileName;
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate ticket PDF", e);
        }
    }
}