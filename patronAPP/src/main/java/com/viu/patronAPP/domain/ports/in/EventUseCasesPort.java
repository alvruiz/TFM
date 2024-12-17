package com.viu.patronAPP.domain.ports.in;

import com.viu.patronAPP.domain.model.Event;

import java.util.List;

public interface EventUseCasesPort {
    public Event createEvent(Event event);

    public void deleteEvent(String eventId);

    public Event getEventById(String eventId);

    public void suscribeOrUnsuscribeEvent(String userId, String eventId);

    public List<Event> getEventByFestivityId(String festivityId);

    public List<Event> getEventByUserId(String userId);
}
