package com.viu.patronAPP.infrastructure.out.persistence.mapper;

import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.FestivityEntity;
import org.springframework.stereotype.Component;

@Component
public class FestivityMapper {
    public static Festivity mapFestivityEntityToDomain(FestivityEntity festivityEntity) {
        return Festivity.builder()
                .id(festivityEntity.getId())
                .name(festivityEntity.getName())
                .startDate(festivityEntity.getStartDate())
                .endDate(festivityEntity.getEndDate())
                .patron(festivityEntity.getPatron())
                .events(festivityEntity.getEvents().stream().map(EventMapper::mapEventEntityToDomain).toList())
                .build();
    }

    public static FestivityEntity mapFestivityDomainToEntity(Festivity festivity) {
        return FestivityEntity.builder()
                .id(festivity.getId())
                .name(festivity.getName())
                .startDate(festivity.getStartDate())
                .endDate(festivity.getEndDate())
                .patron(festivity.getPatron())
                .events(festivity.getEvents().stream().map(EventMapper::mapEventDomainToEntity).toList())
                .build();
    }
}
