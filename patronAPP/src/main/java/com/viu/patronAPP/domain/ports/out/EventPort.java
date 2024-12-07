package com.viu.patronAPP.domain.ports.out;

import com.viu.patronAPP.domain.model.Event;

public interface EventPort {

    public Event createEvent(Event event);

    public Event getEventById(String eventId);

    public void deleteEvent(String eventId);

    public void updateEvent(String eventId, Event event);
}
