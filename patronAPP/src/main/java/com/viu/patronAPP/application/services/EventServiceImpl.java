package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.domain.ports.in.EventUseCasesPort;
import com.viu.patronAPP.domain.ports.out.EventPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventUseCasesPort {

    private final EventPort eventPort;

    @Override
    public Event createEvent(Event event) {
        return eventPort.createEvent(event);
    }

}
