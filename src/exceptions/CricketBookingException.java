package exceptions;

/**
 * Base class for all custom checked exceptions in this application.
 * Demonstrates: Exception Handling (custom exception hierarchy)
 */
public class CricketBookingException extends Exception {
    public CricketBookingException(String message) {
        super(message);
    }
}
