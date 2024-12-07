package com.viu.patronAPP.domain.ports.in;

import com.viu.patronAPP.domain.model.Event;

public interface EventUseCasesPort {
    public Event createEvent(Event event);
}
