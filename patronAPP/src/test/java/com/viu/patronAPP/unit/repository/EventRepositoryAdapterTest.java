package com.viu.patronAPP.unit.repository;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.EventEntity;
import com.viu.patronAPP.infrastructure.out.persistence.repository.EventRepositoryAdapter;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.event.EventMongoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventRepositoryAdapterTest {

    @Mock
    private EventMongoRepository eventRepository;
    @InjectMocks
    private EventRepositoryAdapter eventRepositoryAdapter;

    Event event = Event.builder().id("1").name("test").description("test").startDate(null).endDate(null).coords(List.of()).maxCapacity(0).attendees(new ArrayList<>()).build();
    EventEntity eventEntity = EventEntity.builder().id("1").name("test").description("test").startDate(null).endDate(null).coords(List.of()).maxCapacity(0).attendees(new ArrayList<>()).build();

    @Test
    public void testCreateEvent() {
        when(eventRepository.save(eventEntity)).thenReturn(eventEntity);
        Event result = eventRepositoryAdapter.createEvent(event);
        assert result != null;
        assert result.equals(event);
    }

    @Test
    public void testGetEventById() {
        when(eventRepository.findById("1")).thenReturn(Optional.ofNullable(eventEntity));
        Event result = eventRepositoryAdapter.getEventById("1");
        assert result != null;
        assert result.equals(event);
    }

    @Test
    public void testDeleteEvent() {
        eventRepositoryAdapter.deleteEvent("1");
        verify(eventRepository).deleteById("1");
    }

    @Test
    public void testUpdateEvent() {
        eventRepositoryAdapter.updateEvent("1", event);
        verify(eventRepository).save(eventEntity);
    }

    @Test
    public void testGetEventByUserId() {
        when(eventRepository.findByAttendeesContaining("1")).thenReturn(List.of(eventEntity));
        List<Event> result = eventRepositoryAdapter.getEventByUserId("1");
        assert result != null;
        assert result.size() == 1;
        assert result.getFirst().equals(event);
    }

    @Test
    public void testGetEventByIdsList() {
        when(eventRepository.findByIdIn(List.of("1"))).thenReturn(List.of(eventEntity));
        List<Event> result = eventRepositoryAdapter.getEventByIdsList(List.of("1"));
        assert result != null;
        assert result.size() == 1;
        assert result.getFirst().equals(event);
    }

    @Test
    public void testCountEvents() {
        when(eventRepository.count()).thenReturn(1L);
        int result = eventRepositoryAdapter.countEvents();
        assert result == 1;
    }


}
