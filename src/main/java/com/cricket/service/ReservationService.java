package com.cricket.service;

import com.cricket.model.Match;
import com.cricket.model.Ticket;
import com.cricket.util.AppException;
import com.cricket.util.Validator;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    // Keeps track of all tickets reserved during this app runtime session
    private static final List<Ticket> bookedTicketsDatabase = new ArrayList<>();
    
    public Ticket bookTicket(Match match, String seatNumber, double price) throws AppException {
        if (!Validator.isValidSeat(seatNumber)) {
            throw new AppException("Booking Failed: Seat number format is invalid.");
        }
        
        String systemGeneratedId = "TICK-" + System.currentTimeMillis();
        Ticket newTicket = new Ticket(systemGeneratedId, match, seatNumber, price);
        
        // Save to our booking history list
        bookedTicketsDatabase.add(newTicket);
        return newTicket;
    }

    // Method to fetch the booking history list
    public List<Ticket> getAllBookedTickets() {
        return bookedTicketsDatabase;
    }
}