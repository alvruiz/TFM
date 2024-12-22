package com.viu.patronAPP.domain.ports.out;

import com.viu.patronAPP.domain.model.Event;

import java.util.List;

public interface EventPort {

    public Event createEvent(Event event);

    public Event getEventById(String eventId);

    public void deleteEvent(String eventId);

    public void updateEvent(String eventId, Event event);


    public List<Event> getEventByUserId(String userId);

    public List<Event> getEventByIdsList(List<String> eventIds);
}
