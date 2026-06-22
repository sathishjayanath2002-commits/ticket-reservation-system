package util;

import exceptions.InvalidInputException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Central place for all input validation logic so every UI screen
 * validates consistently. Throws InvalidInputException on failure.
 */
public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    public static void validateNotEmpty(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static void validateEmail(String email) throws InvalidInputException {
        validateNotEmpty(email, "Email");
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidInputException("Please enter a valid email address.");
        }
    }

    public static void validatePassword(String password) throws InvalidInputException {
        validateNotEmpty(password, "Password");
        if (password.length() < 6) {
            throw new InvalidInputException("Password must be at least 6 characters long.");
        }
    }

    public static void validateFutureDate(LocalDateTime dateTime) throws InvalidInputException {
        if (dateTime == null) {
            throw new InvalidInputException("Date/time cannot be empty.");
        }
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new InvalidInputException("Match date/time must be in the future.");
        }
    }

    public static void validatePositiveNumber(double value, String fieldName) throws InvalidInputException {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be greater than zero.");
        }
    }

    public static void validateSeatCount(int requested, int available) throws InvalidInputException {
        if (requested <= 0) {
            throw new InvalidInputException("You must select at least one seat.");
        }
        if (requested > available) {
            throw new InvalidInputException("Only " + available + " seat(s) available.");
        }
    }
}
