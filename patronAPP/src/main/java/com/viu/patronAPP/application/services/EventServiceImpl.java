package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.EventUseCasesPort;
import com.viu.patronAPP.domain.ports.out.EventPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.domain.ports.out.UserPort;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EventServiceImpl implements EventUseCasesPort {

    private final EventPort eventPort;
    private final UserPort userPort;
    private final VillagePort villagePort;
    private final FestivityPort festivityPort;

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

        List<String> attendees = new ArrayList<>((event.getAttendees().stream().map(User::getId).collect(Collectors.toList())));
        ArrayList<User> attendeesModified = new ArrayList<>(event.getAttendees());
        if (attendees.contains(userId)) {
            log.info("User {} is already attendee of event {}", userId, eventId);
            attendees.remove(userId);
            attendeesModified.remove(user);
        } else {
            if (event.getAttendees().size() + 1 > event.getMaxCapacity()) {
                log.info("User {} is too full for event {}", userId, eventId);
                throw new IllegalArgumentException("Event is full");
            }
            attendees.add(userId);
            attendeesModified.add(user);
        }
        event.setAttendees(attendeesModified);
        eventPort.updateEvent(eventId, event);
    }

    @Override
    public List<Event> getEventByFestivityId(String festivityId) {
        Festivity festivity = festivityPort.getById(festivityId);
        return festivity.getEvents();
    }

    @Override
    public List<Event> getEventsByUserId(String userId) {
        List<Event> event = eventPort.getEventByUserId(userId);
        if (event == null) {
            log.info("Event not found for user: {}", userId);
            throw new NotFoundException("Event not found");
        }
        return eventPort.getEventByUserId(userId);
    }

    @Override
    public List<Event> getEventsByIdsList(List<String> eventIds) {
        List<Event> events = eventPort.getEventByIdsList(eventIds);
        if (events == null) {
            log.info("Event not found for user: {}", eventIds);
            throw new NotFoundException("Event not found");
        }
        return events;
    }

}
