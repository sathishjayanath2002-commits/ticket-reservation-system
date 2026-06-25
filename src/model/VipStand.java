package model;

/** VIP stand - top tier seating, double the base price. */
public class VipStand extends Stand {

    public VipStand(int id, int matchId, int totalSeats, double basePrice) {
        super(id, matchId, "VIP", totalSeats, basePrice);
    }

    @Override
    public double calculatePrice() {
        return getBasePrice() * 2.0;
    }
}
