package com.viu.patronAPP.infrastructure.out.persistence.entity.mongo;

import com.viu.patronAPP.domain.model.Coords;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "provinces")
@Builder
@Data
public class ProvinceEntity {
    @Id
    private String id;
    private String name;
    private Coords coords;
    private String imageUrl;
}
