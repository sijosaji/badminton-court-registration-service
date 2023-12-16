package com.justplay.badminton.request;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class TimeSlotCreateRequest {
    @NonNull
    private LocalDateTime startTime;
    @NonNull
    private LocalDateTime endTime;
    @NonNull
    private UUID institutionId;
}
