package com.viu.patronAPP.infrastructure.out.persistence.entity.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "festivities")
@Data
@Builder
public class FestivityEntity {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String patron;
    private String villageId;
}
