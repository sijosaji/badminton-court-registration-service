package com.justplay.badminton.response;

import com.justplay.badminton.model.Booking;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Data
public class TimeSlotBookingResponse {
    private UUID institutionId;
    private UUID id;
    private String startTime;
    private String endTime;
    private List<Booking> bookings;
    private boolean isAvailable;
}
