package com.ticketbooking.service.impl;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import com.ticketbooking.service.PnrGeneratorService;

@Service
public class PnrGeneratorServiceImpl implements PnrGeneratorService {
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int PNR_LENGTH = 10;
    private final SecureRandom random = new SecureRandom();
    
    @Override
    public String generatePnr() {
        StringBuilder pnr = new StringBuilder(PNR_LENGTH);
        for (int i = 0; i < PNR_LENGTH; i++) {
            pnr.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return pnr.toString();
    }
}