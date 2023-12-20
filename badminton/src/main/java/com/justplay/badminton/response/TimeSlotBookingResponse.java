package com.justplay.badminton.response;

import com.justplay.badminton.model.CourtBooking;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class TimeSlotBookingResponse {
    private UUID institutionId;
    private UUID id;
    private String startTime;
    private String endTime;
    private List<CourtBooking> bookings;
}
