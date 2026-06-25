package exceptions;

/** Thrown when a customer requests more seats than are available in a stand. */
public class InsufficientSeatsException extends CricketBookingException {
    public InsufficientSeatsException(String message) {
        super(message);
    }
}
