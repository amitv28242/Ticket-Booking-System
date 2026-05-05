package com.ticketbooking.service;

import java.util.List;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.SearchRequest;
import com.ticketbooking.dto.SearchResponse;

public interface SearchService {
    ApiResponse<List<SearchResponse>> searchTransports(SearchRequest request);
    ApiResponse<List<String>> getPopularSources();
    ApiResponse<List<String>> getPopularDestinations();
}