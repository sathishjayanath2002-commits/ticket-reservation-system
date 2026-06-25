package exceptions;

/** Thrown when login credentials are incorrect or the account doesn't exist. */
public class InvalidLoginException extends CricketBookingException {
    public InvalidLoginException(String message) {
        super(message);
    }
}
