package com.justplay.badminton.model;

import com.justplay.badminton.enums.BookingStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Data
public class Booking {
    private UUID bookingId;
    private User user;
    private Map<UUID, List<Court>> bookings;
    private BookingStatus status;
    private String transactionId;
    private BigDecimal amount;
}
