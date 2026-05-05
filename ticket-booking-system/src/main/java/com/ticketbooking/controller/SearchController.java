package com.ticketbooking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ticketbooking.dto.ApiResponse;
import com.ticketbooking.dto.SearchRequest;
import com.ticketbooking.dto.SearchResponse;
import com.ticketbooking.service.SearchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    // Manual Constructor
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/transports")
    public ResponseEntity<ApiResponse<List<SearchResponse>>> searchTransports(
            @Valid @RequestBody SearchRequest request) {

        return ResponseEntity.ok(
                searchService.searchTransports(request)
        );
    }

    @GetMapping("/popular-sources")
    public ResponseEntity<ApiResponse<List<String>>> getPopularSources() {

        return ResponseEntity.ok(
                searchService.getPopularSources()
        );
    }

    @GetMapping("/popular-destinations")
    public ResponseEntity<ApiResponse<List<String>>> getPopularDestinations() {

        return ResponseEntity.ok(
                searchService.getPopularDestinations()
        );
    }
}