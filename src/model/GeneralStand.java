package model;

/** General stand - no markup, base price as-is. */
public class GeneralStand extends Stand {

    public GeneralStand(int id, int matchId, int totalSeats, double basePrice) {
        super(id, matchId, "General", totalSeats, basePrice);
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}
