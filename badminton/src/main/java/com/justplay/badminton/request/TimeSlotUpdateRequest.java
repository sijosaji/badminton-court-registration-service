package com.justplay.badminton.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TimeSlotUpdateRequest {
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
