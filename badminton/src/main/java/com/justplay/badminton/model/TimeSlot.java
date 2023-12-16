package com.justplay.badminton.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;
import java.util.UUID;

@Data
@Document(indexName = "time-slots", writeTypeHint = WriteTypeHint.FALSE)

public class TimeSlot {
@Id
private UUID id;
private String startTime;
private String endTime;
private UUID institutionId;

}
