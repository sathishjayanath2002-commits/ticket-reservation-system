package model;

/** Pavilion stand - premium seating, 50% markup over base price. */
public class PavilionStand extends Stand {

    public PavilionStand(int id, int matchId, int totalSeats, double basePrice) {
        super(id, matchId, "Pavilion", totalSeats, basePrice);
    }

    @Override
    public double calculatePrice() {
        return getBasePrice() * 1.5;
    }
}
