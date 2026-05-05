package com.ticketbooking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.RunningStatusDTO;
import com.ticketbooking.enums.TransportType;
import com.ticketbooking.service.RunningStatusService;

@RestController
@RequestMapping("/api/running-status")
public class RunningStatusController {

    private final RunningStatusService runningStatusService;

    // Manual Constructor
    public RunningStatusController(RunningStatusService runningStatusService) {
        this.runningStatusService = runningStatusService;
    }

    @GetMapping("/{transportNumber}")
    public ResponseEntity<ApiResponse<RunningStatusDTO>> getRunningStatus(
            @PathVariable String transportNumber,
            @RequestParam TransportType type,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return ResponseEntity.ok(
                runningStatusService.getRunningStatus(
                        transportNumber,
                        type,
                        date
                )
        );
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<RunningStatusDTO>>> getRunningStatusByType(
            @PathVariable TransportType type,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return ResponseEntity.ok(
                runningStatusService.getRunningStatusByType(type, date)
        );
    }

    @GetMapping("/{transportNumber}/history")
    public ResponseEntity<ApiResponse<List<RunningStatusDTO>>> getRunningStatusHistory(
            @PathVariable String transportNumber) {

        return ResponseEntity.ok(
                runningStatusService.getRunningStatusHistory(transportNumber)
        );
    }
}