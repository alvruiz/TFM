package com.viu.patronAPP.infrastructure.out.persistence.repository;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.domain.ports.out.EventPort;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.EventEntity;
import com.viu.patronAPP.infrastructure.out.persistence.mapper.user.EventMapper;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.event.EventMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class EventRepositoryAdapter implements EventPort {

    private final EventMongoRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        EventEntity entityEntity = EventMapper.mapEventDomainToEntity(event);
        return EventMapper.mapEventEntityToDomain(eventRepository.save(entityEntity));
    }
}
