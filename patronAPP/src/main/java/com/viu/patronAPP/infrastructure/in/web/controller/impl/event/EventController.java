package com.viu.patronAPP.infrastructure.in.web.controller.impl.event;

import com.viu.patronAPP.infrastructure.DTO.event.EventDTO;
import com.viu.patronAPP.infrastructure.DTO.event.SubscribeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/event")
public interface EventController {
    @PostMapping("/")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO);

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable String eventId);

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeOrUnsubscribeEvent(@RequestBody SubscribeDTO suscribeDTO);
}
