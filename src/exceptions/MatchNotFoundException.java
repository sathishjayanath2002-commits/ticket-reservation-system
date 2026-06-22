package exceptions;

/** Thrown when a requested match ID doesn't exist in the system. */
public class MatchNotFoundException extends CricketBookingException {
    public MatchNotFoundException(String message) {
        super(message);
    }
}
