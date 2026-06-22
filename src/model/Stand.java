package model;

/**
 * Represents a seating stand/block within a stadium for a given match
 * (e.g., Pavilion, General, VIP). Each stand belongs to one Match and
 * contains many Seats.
 *
 * Demonstrates: Abstraction + Polymorphism — calculatePrice() is overridden
 * differently by each stand type to show varied pricing logic.
 */
public abstract class Stand {
    private int id;
    private int matchId;
    private String standName;
    private int totalSeats;
    private double basePrice;

    public Stand(int id, int matchId, String standName, int totalSeats, double basePrice) {
        this.id = id;
        this.matchId = matchId;
        this.standName = standName;
        this.totalSeats = totalSeats;
        this.basePrice = basePrice;
    }

    // Polymorphic method - each stand type calculates final price differently
    public abstract double calculatePrice();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMatchId() { return matchId; }
    public void setMatchId(int matchId) { this.matchId = matchId; }

    public String getStandName() { return standName; }
    public void setStandName(String standName) { this.standName = standName; }

    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
}
