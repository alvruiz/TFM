package com.viu.patronAPP.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Festivity {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String patron;
    private List<Event> events;
}
