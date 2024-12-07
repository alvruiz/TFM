package com.viu.patronAPP.infrastructure.out.persistence.entity.mongo;

import com.viu.patronAPP.domain.model.Coords;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "events")
@Data
@Builder
public class EventEntity {
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Coords coords;
    private Integer maxCapacity;
    private int[] attendees;
    private String festivityId;
}
