package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a confirmed (or cancelled) ticket booking made by a Customer
 * for a specific Match, covering one or more Seats.
 * Demonstrates: Encapsulation, Composition (a Booking is made up of Seats)
 */
public class Booking {
    private int id;
    private int userId;
    private int matchId;
    private List<Integer> seatIds;
    private LocalDateTime bookingDate;
    private double totalAmount;
    private String status; // "CONFIRMED", "CANCELLED"

    public Booking(int id, int userId, int matchId, List<Integer> seatIds,
                    LocalDateTime bookingDate, double totalAmount, String status) {
        this.id = id;
        this.userId = userId;
        this.matchId = matchId;
        this.seatIds = seatIds;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getMatchId() { return matchId; }
    public void setMatchId(int matchId) { this.matchId = matchId; }

    public List<Integer> getSeatIds() { return seatIds; }
    public void setSeatIds(List<Integer> seatIds) { this.seatIds = seatIds; }

    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
