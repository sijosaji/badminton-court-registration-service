package com.justplay.badminton.resources;

import com.justplay.badminton.request.TimeSlotCreateRequest;
import com.justplay.badminton.request.TimeSlotUpdateRequest;
import com.justplay.badminton.response.TimeSlotBookingResponse;
import com.justplay.badminton.service.TimeSlotService;
import jakarta.annotation.Nonnull;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.justplay.badminton.Constants.INSTITUTION_ID;
import static com.justplay.badminton.Constants.TIME_SLOT;
import static com.justplay.badminton.Constants.TIME_SLOT_ID;
import static com.justplay.badminton.Constants.TIME_SLOT_IDS;

/**
 * Resource Class for basic timeslot CRUD operations.
 */
@RestController
@RequestMapping(TIME_SLOT)
public class TimeSlotResource {
    @Autowired
    private TimeSlotService timeSlotService;
    @GetMapping(path = TIME_SLOT_ID)
    public TimeSlotBookingResponse getTimeSlot(@Nonnull @PathParam(INSTITUTION_ID)UUID institutionId,
            @PathVariable(TIME_SLOT_ID) UUID timeSlotId) {
        return timeSlotService.getBookingDetailsForGivenTimeSlot(institutionId, timeSlotId);
    }
    @PostMapping
    public List<TimeSlotBookingResponse> createTimeSlot(@RequestBody List<TimeSlotCreateRequest> timeSlotCreateRequests) {
        return timeSlotService.createTimeSlots(timeSlotCreateRequests);
    }
    @PutMapping
    public List<TimeSlotBookingResponse> updateTimeSlot(@RequestBody List<TimeSlotUpdateRequest> timeSlotUpdateRequests) {
        return timeSlotService.updateTimeSlots(timeSlotUpdateRequests);
    }
    @DeleteMapping
    public ResponseEntity deleteTimeSlots(@RequestParam(TIME_SLOT_IDS) List<UUID> timeSlotIds) {
        return timeSlotService.deleteTimeSlots(timeSlotIds);
    }

}
