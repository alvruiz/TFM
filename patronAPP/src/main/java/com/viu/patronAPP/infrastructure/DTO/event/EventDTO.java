package com.viu.patronAPP.infrastructure.DTO.event;

import com.viu.patronAPP.domain.model.Coords;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class EventDTO {
    private String id;
    private String eventName;
    private String eventDescription;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private List<Coords> coords;
    private List<String> attendees;
    private int eventMaxCapacity;
    private String eventFestivityId;
}
