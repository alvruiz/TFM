package com.viu.patronAPP.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Event {

    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Coords coords;
    private int maxCapacity;
    private int[] attendees;
    private String festivityId;
}
