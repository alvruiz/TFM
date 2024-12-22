package com.viu.patronAPP.infrastructure.out.persistence.repository;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.domain.ports.out.EventPort;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.EventEntity;
import com.viu.patronAPP.infrastructure.out.persistence.mapper.EventMapper;
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
    public List<Event> getEventByUserId(String userId) {
        return eventRepository.findByAttendeesContaining(userId).stream().map(EventMapper::mapEventEntityToDomain).toList();
    }

    @Override
    public List<Event> getEventByIdsList(List<String> eventIds) {
        List<EventEntity> events = eventRepository.findByIdIn(eventIds).stream().toList();
        return eventRepository.findByIdIn(eventIds).stream().map(EventMapper::mapEventEntityToDomain).toList();
    }

    public int countEvents() {
        return (int) eventRepository.count();
    }


}

