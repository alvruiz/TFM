package com.viu.patronAPP.domain.ports.out;

import com.viu.patronAPP.domain.model.Event;

public interface EventPort {

    public Event createEvent(Event event);
}
