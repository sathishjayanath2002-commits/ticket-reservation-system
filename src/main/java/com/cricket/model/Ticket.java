package com.cricket.model;

public class Ticket {
    private String ticketId;
    private Match match;
    private String seatNumber;
    private double price;

    public Ticket(String ticketId, Match match, String seatNumber, double price) {
        this.ticketId = ticketId;
        this.match = match;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public String getTicketId() { return ticketId; }
    public Match getMatch() { return match; }
    public String getSeatNumber() { return seatNumber; }
    public double getPrice() { return price; }
}