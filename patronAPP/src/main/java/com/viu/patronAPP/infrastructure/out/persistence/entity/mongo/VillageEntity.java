package com.viu.patronAPP.infrastructure.out.persistence.entity.mongo;

import com.viu.patronAPP.domain.model.Coords;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "villages")
public class VillageEntity {
    private String id;
    private String name;
    private Coords coords;
    private String imageUrl;
    @DBRef
    private FestivityEntity festivity;
    @DBRef
    private ProvinceEntity province;
}
