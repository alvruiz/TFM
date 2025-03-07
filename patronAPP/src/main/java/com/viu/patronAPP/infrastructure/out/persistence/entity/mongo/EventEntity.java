package com.viu.patronAPP.infrastructure.out.persistence.entity.mongo;

import com.viu.patronAPP.domain.model.Coords;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "events")
@Data
@Builder
public class EventEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Coords> coords;
    private Integer maxCapacity;
    @DBRef
    private List<UserEntity> attendees;
}
