package com.cricket.service;

import com.cricket.model.Match;
import com.cricket.model.Ticket;
import com.cricket.util.AppException;
import com.cricket.util.Validator;

public class ReservationService {
    
    public Ticket bookTicket(Match match, String seatNumber, double price) throws AppException {
        // Enforces the Validation logic requirement
        if (!Validator.isValidSeat(seatNumber)) {
            throw new AppException("Booking Failed: Seat number format is invalid.");
        }
        
        String systemGeneratedId = "TICK-" + System.currentTimeMillis();
        System.out.println("Processing ticket reservation in service layer...");
        return new Ticket(systemGeneratedId, match, seatNumber, price);
    }
}