package com.justplay.badminton.resources;

import com.justplay.badminton.request.TimeSlotCreateRequest;
import com.justplay.badminton.request.TimeSlotUpdateRequest;
import com.justplay.badminton.response.TimeSlotBookingResponse;
import com.justplay.badminton.service.TimeSlotService;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

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

    /**
     * Endpoint gets booking details with timings for a given time slot id.
     * @param timeSlotId Id of the time slot whose booking details needs to be fetched.
     * @return {@link TimeSlotBookingResponse} Returns booking details of a given time slot
     */
    @GetMapping(path = TIME_SLOT_ID)
    public TimeSlotBookingResponse getTimeSlot(@PathVariable(TIME_SLOT_ID) UUID timeSlotId) {
        return timeSlotService.getBookingDetailsForGivenTimeSlot(timeSlotId);
    }

    /**
     * Endpoint to create Bulk time slots.
     * @param timeSlotCreateRequests List of time slots that needs to be created.
     * @return {@link List<TimeSlotBookingResponse>} returns List of time slots that are created.
     */
    @PostMapping
    public List<TimeSlotBookingResponse> createTimeSlot(@Valid @NotNull
        @RequestBody List<TimeSlotCreateRequest> timeSlotCreateRequests) {
        return timeSlotService.createTimeSlots(timeSlotCreateRequests);
    }

    /**
     * Endpoint to create Bulk update slots.
     * @param timeSlotUpdateRequests List of time slots that needs to be bulk updated.
     * @return {@link List<TimeSlotBookingResponse>} returns List of time slots that are updated.
     */
    @PutMapping
    public List<TimeSlotBookingResponse> updateTimeSlot(@Valid @NotNull
        @RequestBody List<TimeSlotUpdateRequest> timeSlotUpdateRequests) {
        return timeSlotService.updateTimeSlots(timeSlotUpdateRequests);
    }

    /**
     * Endpoint to bulk delete time slots for given Ids.
     * @param timeSlotIds List of time slot Ids to be deleted.
     * @return {@link ResponseEntity} Returns success if following timeSlotIds are present else will return BAD request.
     */
    @DeleteMapping
    public ResponseEntity deleteTimeSlots(@RequestParam(TIME_SLOT_IDS) List<UUID> timeSlotIds) {
        return timeSlotService.deleteTimeSlots(timeSlotIds);
    }

}
