package com.justplay.badminton.dao;

import com.justplay.badminton.model.TimeSlot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TimeSlotBookingRepository extends ElasticsearchRepository<TimeSlot, UUID> {
}
