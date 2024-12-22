package com.viu.patronAPP.infrastructure.out.persistence.entity.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "festivities")
@Data
@Builder
public class FestivityEntity {
    @Id
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String patron;
    @DBRef
    private List<EventEntity> events;
}
