package com.viu.patronAPP.infrastructure.out.persistence.mapper;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public static Event mapEventEntityToDomain(EventEntity eventEntity) {
        return Event.builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .description(eventEntity.getDescription())
                .startDate(eventEntity.getStartDate())
                .endDate(eventEntity.getEndDate())
                .coords(eventEntity.getCoords())
                .maxCapacity(eventEntity.getMaxCapacity())
                .attendees(eventEntity.getAttendees().stream().map(UserMapper::mapUserEntityToDomain).toList())
                .build();
    }

    public static EventEntity mapEventDomainToEntity(Event event) {
        return EventEntity.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .coords(event.getCoords())
                .maxCapacity(event.getMaxCapacity())
                .attendees(event.getAttendees().stream().map(UserMapper::mapUserDomainToEntity).toList())
                .build();
    }
}
