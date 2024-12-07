package com.viu.patronAPP.infrastructure.DTO.user;

import com.viu.patronAPP.domain.model.Coords;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class EventDTO {
    private int eventID;
    private String eventName;
    private String eventDescription;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private Coords coords;
    private int[] attendees;
    private int eventMaxCapacity;
    private String eventFestivityId;
}
