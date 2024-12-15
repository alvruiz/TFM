package com.viu.patronAPP.infrastructure.out.persistence.repository;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.domain.ports.out.EventPort;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.EventEntity;
import com.viu.patronAPP.infrastructure.out.persistence.mapper.user.EventMapper;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.event.EventMongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class EventRepositoryAdapter implements EventPort {

    private final EventMongoRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        EventEntity entityEntity = EventMapper.mapEventDomainToEntity(event);
        return EventMapper.mapEventEntityToDomain(eventRepository.save(entityEntity));
    }

    @Override
    public Event getEventById(String eventId) {
        Optional<EventEntity> eventOptional = eventRepository.findById(eventId);
        return eventOptional.map(EventMapper::mapEventEntityToDomain).orElse(null);
    }

    @Override
    public void deleteEvent(String eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public void updateEvent(String id, Event event) {
        EventEntity entityEntity = EventMapper.mapEventDomainToEntity(event);
        entityEntity.setId(id);
        eventRepository.save(entityEntity);
    }

    @Override
    public List<Event> getEventByFestivityId(String festivityId) {
        return eventRepository.findByFestivityId(festivityId).stream().map(EventMapper::mapEventEntityToDomain).toList();
    }
}

