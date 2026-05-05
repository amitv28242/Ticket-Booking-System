package com.ticketbooking.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.RunningStatusDTO;
import com.ticketbooking.entity.RunningStatus;
import com.ticketbooking.enums.TransportType;
import com.ticketbooking.exception.ResourceNotFoundException;
import com.ticketbooking.repository.RunningStatusRepository;
import com.ticketbooking.service.RunningStatusService;

@Service
public class RunningStatusServiceImpl implements RunningStatusService {
    
    private final RunningStatusRepository runningStatusRepository;
    
    public RunningStatusServiceImpl(RunningStatusRepository runningStatusRepository) {
        this.runningStatusRepository = runningStatusRepository;
    }
    
    @Override
    public ApiResponse<RunningStatusDTO> getRunningStatus(String transportNumber, TransportType type, LocalDate date) {
        RunningStatus status = runningStatusRepository.findByTransportNumberAndTypeAndDate(transportNumber, type, date)
                .orElseThrow(() -> new ResourceNotFoundException("Running status not found"));
        return ApiResponse.success(mapToDTO(status), "Running status retrieved");
    }
    
    @Override
    public ApiResponse<List<RunningStatusDTO>> getRunningStatusByType(TransportType type, LocalDate date) {
        List<RunningStatusDTO> statuses = runningStatusRepository.findByTransportTypeAndJourneyDate(type, date)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
        return ApiResponse.success(statuses, "Running statuses retrieved");
    }
    
    @Override
    public ApiResponse<List<RunningStatusDTO>> getRunningStatusHistory(String transportNumber) {
        List<RunningStatusDTO> statuses = runningStatusRepository.findByTransportNumberOrderByLastUpdatedDesc(transportNumber)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
        return ApiResponse.success(statuses, "Running status history retrieved");
    }
    
    private RunningStatusDTO mapToDTO(RunningStatus status) {
        return RunningStatusDTO.builder()
                .id(status.getId())
                .transportNumber(status.getTransportNumber())
                .transportType(status.getTransportType())
                .currentStation(status.getCurrentStation())
                .nextStation(status.getNextStation())
                .status(status.getStatus())
                .delayMinutes(status.getDelayMinutes())
                .delayReason(status.getDelayReason())
                .journeyDate(status.getJourneyDate())
                .platformNumber(status.getPlatformNumber())
                .expectedArrival(status.getExpectedArrival())
                .expectedDeparture(status.getExpectedDeparture())
                .lastUpdated(status.getLastUpdated())
                .build();
    }
}