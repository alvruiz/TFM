package com.viu.patronAPP.domain.ports.in;

import com.viu.patronAPP.domain.model.Event;

public interface EventUseCasesPort {
    public Event createEvent(Event event);

    public void deleteEvent(String eventId);

    public Event getEventById(String eventId);

    public void suscribeOrUnsuscribeEvent(String userId, String eventId);
}
