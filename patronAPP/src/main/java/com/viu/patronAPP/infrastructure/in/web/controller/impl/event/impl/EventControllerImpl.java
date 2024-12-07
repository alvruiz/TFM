package com.viu.patronAPP.infrastructure.in.web.controller.impl.event.impl;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.domain.ports.in.EventUseCasesPort;
import com.viu.patronAPP.infrastructure.DTO.user.EventDTO;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.event.EventController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EventControllerImpl implements EventController {


    private final EventUseCasesPort eventUseCasesPort;

    @Override
    public ResponseEntity<EventDTO> createEvent(EventDTO eventDTO) {
        Event event = Event.builder()
                .name(eventDTO.getEventName())
                .description(eventDTO.getEventDescription())
                .startDate(eventDTO.getEventStartDate())
                .endDate(eventDTO.getEventEndDate())
                .coords(eventDTO.getCoords())
                .maxCapacity(eventDTO.getEventMaxCapacity())
                .attendees(eventDTO.getAttendees())
                .festivityId(eventDTO.getEventFestivityId())
                .build();
        eventUseCasesPort.createEvent(event);
        return ResponseEntity.ok(eventDTO);
    }

}
