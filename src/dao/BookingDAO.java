package dao;

import model.Booking;
import java.util.List;

/**
 * Data access contract for Booking-related DB operations.
 * Member working on Booking module implements this.
 */
public interface BookingDAO {
    boolean createBooking(Booking booking);
    List<Booking> getBookingsByUserId(int userId);
    boolean cancelBooking(int bookingId);
}
