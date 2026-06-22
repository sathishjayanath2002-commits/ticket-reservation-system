package dao;

import model.Seat;
import java.util.List;

/**
 * Data access contract for Seat-related DB operations.
 * Member working on Seating module implements this.
 */
public interface SeatDAO {
    List<Seat> getSeatsByStandId(int standId);
    boolean markSeatsAsBooked(List<Integer> seatIds);
    boolean markSeatsAsAvailable(List<Integer> seatIds);
}
