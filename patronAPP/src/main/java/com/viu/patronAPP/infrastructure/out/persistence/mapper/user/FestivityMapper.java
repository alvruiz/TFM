package com.viu.patronAPP.infrastructure.out.persistence.mapper.user;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.FestivityEntity;
import org.springframework.stereotype.Component;

@Component
public class FestivityMapper {
    public static Festivity mapFestivityEntityToDomain(FestivityEntity festivityEntity) {
        return Festivity.builder()
                .name(festivityEntity.getName())
                .startDate(festivityEntity.getStartDate())
                .endDate(festivityEntity.getEndDate())
                .patron(festivityEntity.getPatron())
                .villageId(festivityEntity.getVillageId())
                .build();
    }

    public static FestivityEntity mapFestivityDomainToEntity(Festivity festivity) {
        return FestivityEntity.builder()
                .name(festivity.getName())
                .startDate(festivity.getStartDate())
                .endDate(festivity.getEndDate())
                .patron(festivity.getPatron())
                .villageId(festivity.getVillageId())
                .build();
    }
}
