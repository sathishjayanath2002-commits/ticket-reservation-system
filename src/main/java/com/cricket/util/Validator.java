package com.cricket.util;

public class Validator {
    
    // Validates seat formatting layout (e.g., Row A-F followed by numbers 1-99)
    public static boolean isValidSeat(String seatNumber) {
        if (seatNumber == null || seatNumber.isEmpty()) {
            return false;
        }
        return seatNumber.matches("^[A-F][0-9]{1,2}$");
    }

    public static boolean isEmpty(String data) {
        return data == null || data.trim().isEmpty();
    }
}