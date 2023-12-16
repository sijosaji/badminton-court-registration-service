package com.justplay.badminton.model;

import lombok.Data;

import java.util.UUID;
@Data
public class Booking {
    private UUID bookingId;
    private UUID courtId;
    private int noOfPlayers;
    private User user;
    private UUID timeSlotId;
}
