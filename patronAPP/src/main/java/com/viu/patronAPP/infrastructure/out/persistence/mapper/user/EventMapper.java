package com.viu.patronAPP.infrastructure.out.persistence.mapper.user;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public static Event mapEventEntityToDomain(EventEntity eventEntity) {
        return Event.builder()
                .name(eventEntity.getName())
                .description(eventEntity.getDescription())
                .startDate(eventEntity.getStartDate())
                .endDate(eventEntity.getEndDate())
                .coords(eventEntity.getCoords())
                .maxCapacity(eventEntity.getMaxCapacity())
                .attendees(eventEntity.getAttendees())
                .festivityId(eventEntity.getFestivityId())
                .build();
    }

    public static EventEntity mapEventDomainToEntity(Event event) {
        return EventEntity.builder()
                .name(event.getName())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .coords(event.getCoords())
                .maxCapacity(event.getMaxCapacity())
                .attendees(event.getAttendees())
                .festivityId(event.getFestivityId())
                .build();
    }
}
