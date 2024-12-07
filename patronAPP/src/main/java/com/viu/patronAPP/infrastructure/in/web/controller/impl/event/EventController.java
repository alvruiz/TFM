package com.viu.patronAPP.infrastructure.in.web.controller.impl.event;

import com.viu.patronAPP.infrastructure.DTO.user.EventDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/event")
public interface EventController {

    @PostMapping("/")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO);
}
