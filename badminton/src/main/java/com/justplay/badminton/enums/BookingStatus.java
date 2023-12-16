package com.justplay.badminton.enums;

public enum BookingStatus {
    IN_PROGRESS,
    BOOKED,
    CANCELLED;

    public static BookingStatus fromString(String status) {
        for (BookingStatus value : values()) {
            if (value.name().equalsIgnoreCase(status)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid booking status: " + status);
    }
}
