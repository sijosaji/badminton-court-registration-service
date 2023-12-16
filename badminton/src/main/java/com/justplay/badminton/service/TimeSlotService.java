package com.justplay.badminton.service;

import com.justplay.badminton.dao.TimeSlotBookingRepository;
import com.justplay.badminton.model.TimeSlot;
import com.justplay.badminton.request.TimeSlotCreateRequest;
import com.justplay.badminton.request.TimeSlotUpdateRequest;
import com.justplay.badminton.response.TimeSlotBookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TimeSlotService {
    @Autowired
    private TimeSlotBookingRepository repository;
    public TimeSlotBookingResponse getBookingDetailsForGivenTimeSlot(UUID institutionId, UUID timeSlotId) {
     Optional<TimeSlot> bookingFromEs = repository.findById(timeSlotId);
     return bookingFromEs.map(this::mapTimeLotToResponse).orElse(null);
    }

    public List<TimeSlotBookingResponse> createTimeSlots(List<TimeSlotCreateRequest> timeSlotCreateRequests) {
        List<TimeSlot> timeSlots = timeSlotCreateRequests.stream().map(timeSlotCreateRequest -> {
            TimeSlot slot = new TimeSlot();
            slot.setId(UUID.randomUUID());
            slot.setStartTime(timeSlotCreateRequest.getStartTime().toString());
            slot.setEndTime(timeSlotCreateRequest.getEndTime().toString());
            slot.setInstitutionId(timeSlotCreateRequest.getInstitutionId());
            return slot;
        }).collect(Collectors.toList());
        repository.saveAll(timeSlots);
        return timeSlots.stream().map(this::mapTimeLotToResponse).toList();
    }
    private TimeSlotBookingResponse mapTimeLotToResponse(TimeSlot slot) {
        TimeSlotBookingResponse response = new TimeSlotBookingResponse();
        response.setId(slot.getId());
        response.setStartTime(slot.getStartTime());
        response.setEndTime(slot.getEndTime());
        response.setBookings(List.of());
        response.setInstitutionId(slot.getInstitutionId());
        return response;
    }

    public List<TimeSlotBookingResponse> updateTimeSlots(List<TimeSlotUpdateRequest> timeSlotUpdateRequests) {
        List<UUID> timeSlotIds = timeSlotUpdateRequests.stream().map(TimeSlotUpdateRequest::getId).toList();
        Map<UUID,TimeSlot> slots = ((List<TimeSlot>) repository.findAllById(timeSlotIds)).stream().
                collect(Collectors.toMap(TimeSlot::getId, Function.identity()));
        timeSlotUpdateRequests.forEach(timeSlotUpdateRequest -> {
            TimeSlot slot = slots.get(timeSlotUpdateRequest.getId());
            if (Objects.nonNull(timeSlotUpdateRequest.getStartTime())) {
                slot.setStartTime(timeSlotUpdateRequest.getStartTime().toString());
            }
            if (Objects.nonNull(timeSlotUpdateRequest.getEndTime())) {
                slot.setEndTime(timeSlotUpdateRequest.getEndTime().toString());
            }
        });
        repository.saveAll(slots.values());
        List<TimeSlot> updatedTimeSlots = ((List<TimeSlot>) repository.findAllById(timeSlotIds));
        return updatedTimeSlots.stream().map(this::mapTimeLotToResponse).toList();
    }

    public ResponseEntity deleteTimeSlots(List<UUID> timeSlotIds) {
        List<TimeSlot> timeSlots = (List<TimeSlot>) repository.findAllById(timeSlotIds);
        if (timeSlotIds.size() == timeSlots.size()) {
            repository.deleteAllById(timeSlotIds);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
