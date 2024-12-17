package com.viu.patronAPP.infrastructure.in.web.controller.impl.event.impl;

import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.domain.model.Village;
import com.viu.patronAPP.domain.ports.in.EventUseCasesPort;
import com.viu.patronAPP.domain.ports.in.FestivityUseCasesPort;
import com.viu.patronAPP.domain.ports.in.UserUseCasesPort;
import com.viu.patronAPP.domain.ports.in.VillageUseCasesPort;
import com.viu.patronAPP.infrastructure.DTO.event.EventAndVillageDTO;
import com.viu.patronAPP.infrastructure.DTO.event.EventDTO;
import com.viu.patronAPP.infrastructure.DTO.event.SubscribeDTO;
import com.viu.patronAPP.infrastructure.DTO.province.VillageDTO;
import com.viu.patronAPP.infrastructure.DTO.user.UserDTO;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.event.EventController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class EventControllerImpl implements EventController {


    private final EventUseCasesPort eventUseCasesPort;
    private final VillageUseCasesPort villageUseCasesPort;
    private final FestivityUseCasesPort festivityUseCasesPort;
    private final UserUseCasesPort userUseCasesPort;

    @Override
    public ResponseEntity<EventDTO> createEvent(EventDTO eventDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
        eventDTO.setId(event.getId());
        return ResponseEntity.ok(eventDTO);
    }

    @Override
    public ResponseEntity<List<EventDTO>> getEventByFestivityId(String festivityId) {
        List<Event> event = eventUseCasesPort.getEventByFestivityId(festivityId);
        return ResponseEntity.ok(event.stream().map(eventDTO -> EventDTO.builder().id(eventDTO.getId()).eventName(eventDTO.getName()).eventDescription(eventDTO.getDescription()).eventStartDate(eventDTO.getStartDate()).eventEndDate(eventDTO.getEndDate()).coords(eventDTO.getCoords()).eventMaxCapacity(eventDTO.getMaxCapacity()).attendees(eventDTO.getAttendees()).eventFestivityId(eventDTO.getFestivityId()).build()).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<String> deleteEvent(String eventId) {
        eventUseCasesPort.deleteEvent(eventId);
        return ResponseEntity.ok("Deleted");
    }

    @Override
    public ResponseEntity<UserDTO> subscribeOrUnsubscribeEvent(SubscribeDTO subscribeDTO) {
        User user = userUseCasesPort.getUserByEmail(subscribeDTO.getEmail());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        User userUpdated = userUseCasesPort.updateUser(user);
        List<String> events = user.getEventsParticipating();
        if (events.contains(subscribeDTO.getEventId())) {
            events = events.stream().filter(eventId -> !eventId.equals(subscribeDTO.getEventId())).toList();
        } else {
            events.add(subscribeDTO.getEventId());
        }
        eventUseCasesPort.suscribeOrUnsuscribeEvent(user.getId(), subscribeDTO.getEventId());
        user.setEventsParticipating(events);
        userUseCasesPort.updateUser(user);
        return ResponseEntity.ok(UserDTO.builder()
                .name(userUpdated.getName())
                .surname(userUpdated.getSurname())
                .email(userUpdated.getEmail())
                .age(userUpdated.getAge())
                .gender(userUpdated.getGender())
                .rol(userUpdated.getRol())
                .imageUrl(userUpdated.getImageUrl())
                .eventsParticipating(userUpdated.getEventsParticipating())
                .build());
    }

    @Override
    public ResponseEntity<List<EventAndVillageDTO>> getEventByUserId(String userId) {
        List<Event> events = eventUseCasesPort.getEventByUserId(userId);
        if (events.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }
        Map<String, Festivity> festivityMap = events.stream()
                .map(event -> festivityUseCasesPort.getById(event.getFestivityId()))
                .collect(Collectors.toMap(Festivity::getId, festivity -> festivity));
        Map<String, Village> villageMap = festivityMap.values().stream()
                .map(festivity -> villageUseCasesPort.getVillageById(festivity.getVillageId()))
                .collect(Collectors.toMap(Village::getId, village -> village));
        List<EventAndVillageDTO> result = events.stream()
                .map(event -> {
                    Festivity festivity = festivityMap.get(event.getFestivityId());
                    Village village = villageMap.get(festivity.getVillageId());

                    return EventAndVillageDTO.builder()
                            .id(event.getId())
                            .eventName(event.getName())
                            .eventDescription(event.getDescription())
                            .eventFestivityId(event.getFestivityId())
                            .eventStartDate(event.getStartDate())
                            .eventEndDate(event.getEndDate())
                            .coords(event.getCoords())
                            .eventMaxCapacity(event.getMaxCapacity())
                            .attendees(event.getAttendees())
                            .village(VillageDTO.builder()
                                    .id(village.getId())
                                    .name(village.getName())
                                    .coords(village.getCoords())
                                    .imageUrl(village.getImageUrl())
                                    .provinceId(village.getProvinceId())
                                    .build())
                            .build();
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<EventAndVillageDTO>> getEventsByEmail(String email) {
        User user = userUseCasesPort.getUserByEmail(email);
        return this.getEventByUserId(user.getId());
    }
}
