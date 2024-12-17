package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.EventUseCasesPort;
import com.viu.patronAPP.domain.ports.out.EventPort;
import com.viu.patronAPP.domain.ports.out.UserPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EventServiceImpl implements EventUseCasesPort {

    private final EventPort eventPort;
    private final UserPort userPort;

    @Override
    public Event createEvent(Event event) {
        return eventPort.createEvent(event);
    }

    @Override
    public Event getEventById(String eventId) {
        return eventPort.getEventById(eventId);
    }

    @Override
    public void deleteEvent(String eventId) {
        if (eventPort.getEventById(eventId) == null) {
            log.info("Event not found: {}", eventId);
            throw new NotFoundException("Event not found");
        }
        eventPort.deleteEvent(eventId);
    }

    @Override
    public void suscribeOrUnsuscribeEvent(String userId, String eventId) {
        Event event = eventPort.getEventById(eventId);
        if (event == null) {
            log.info("Event {} not found for user {}", eventId, userId);
            throw new NotFoundException("Event not found");
        }
        User user = userPort.getUserById(userId);
        if (user == null) {
            log.info("User not found for user {}", userId);
            throw new NotFoundException("User not found");
        }

        List<String> attendees = new ArrayList<>((event.getAttendees()));
        if (attendees.contains(userId)) {
            log.info("User {} is already attendee of event {}", userId, eventId);
            attendees.remove(userId);
        } else {
            if (event.getAttendees().size() + 1 > event.getMaxCapacity()) {
                log.info("User {} is too full for event {}", userId, eventId);
                throw new IllegalArgumentException("Event is full");
            }
            attendees.add(userId);
        }
        event.setAttendees(attendees);
        eventPort.updateEvent(eventId, event);
    }

    @Override
    public List<Event> getEventByFestivityId(String festivityId) {
        if (eventPort.getEventByFestivityId(festivityId) == null) {
            log.info("Event not found for festivity id: {}", festivityId);
            throw new NotFoundException("Event not found");
        }
        return eventPort.getEventByFestivityId(festivityId);
    }

    @Override
    public List<Event> getEventByUserId(String userId) {
        List<Event> event = eventPort.getEventByUserId(userId);
        if (event == null) {
            log.info("Event not found for user: {}", userId);
            throw new NotFoundException("Event not found");
        }
        return eventPort.getEventByUserId(userId);
    }

}
