package exceptions;

/** Thrown when form input fails validation (empty fields, bad format, etc.) */
public class InvalidInputException extends CricketBookingException {
    public InvalidInputException(String message) {
        super(message);
    }
}
