package com.ticketbooking.service;

import java.time.LocalDate;
import java.util.List;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.RunningStatusDTO;
import com.ticketbooking.enums.TransportType;

public interface RunningStatusService {
    ApiResponse<RunningStatusDTO> getRunningStatus(String transportNumber, TransportType type, LocalDate date);
    ApiResponse<List<RunningStatusDTO>> getRunningStatusByType(TransportType type, LocalDate date);
    ApiResponse<List<RunningStatusDTO>> getRunningStatusHistory(String transportNumber);
}