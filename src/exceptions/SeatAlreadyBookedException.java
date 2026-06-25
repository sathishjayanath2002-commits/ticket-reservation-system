package exceptions;

/** Thrown when a customer tries to book a seat that's already taken. */
public class SeatAlreadyBookedException extends CricketBookingException {
    public SeatAlreadyBookedException(String message) {
        super(message);
    }
}
