package com.viu.patronAPP.infrastructure.DTO.event;

import com.viu.patronAPP.domain.model.Coords;
import com.viu.patronAPP.infrastructure.DTO.province.VillageDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class EventAndVillageDTO {
    private String id;
    private String eventName;
    private String eventDescription;
    private String eventFestivityId;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private Coords coords;
    private Integer eventMaxCapacity;
    private List<String> attendees;
    private VillageDTO village;
}
