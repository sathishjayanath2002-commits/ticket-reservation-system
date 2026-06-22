package model;

/**
 * Represents a single seat within a Stand.
 * Demonstrates: Encapsulation
 */
public class Seat {
    private int id;
    private int standId;
    private String seatNumber; // e.g. "A1", "B12"
    private boolean isBooked;

    public Seat(int id, int standId, String seatNumber, boolean isBooked) {
        this.id = id;
        this.standId = standId;
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStandId() { return standId; }
    public void setStandId(int standId) { this.standId = standId; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { this.isBooked = booked; }
}
