package com.viu.patronAPP.services;

import com.viu.patronAPP.application.services.EventServiceImpl;
import com.viu.patronAPP.domain.model.Coords;
import com.viu.patronAPP.domain.model.Event;
import com.viu.patronAPP.domain.model.Festivity;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.out.EventPort;
import com.viu.patronAPP.domain.ports.out.FestivityPort;
import com.viu.patronAPP.domain.ports.out.UserPort;
import com.viu.patronAPP.domain.ports.out.VillagePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class EventServiceTest {

    @Mock
    private EventPort eventPort;
    @Mock
    private UserPort userPort;
    @Mock
    private VillagePort villagePort;
    @Mock
    private FestivityPort festivityPort;

    @InjectMocks
    private EventServiceImpl eventService;

    Event testEvent = Event.builder()
            .name("test")
            .description("test")
            .startDate(LocalDateTime.of(2023, 1, 1, 0, 0))
            .endDate(LocalDateTime.of(2023, 1, 2, 0, 0))
            .coords(List.of(Coords.builder().latitude(0).longitude(0).build()))
            .maxCapacity(10)
            .attendees(List.of())
            .build();

    Event testEvent2 = Event.builder()
            .name("test")
            .description("test")
            .startDate(LocalDateTime.of(2023, 1, 1, 0, 0))
            .endDate(LocalDateTime.of(2023, 1, 2, 0, 0))
            .coords(List.of(Coords.builder().latitude(0).longitude(0).build()))
            .maxCapacity(10)
            .attendees(List.of(User.builder().id("1").build()))
            .build();
    Event testEvent2Full = Event.builder()
            .name("test")
            .description("test")
            .startDate(LocalDateTime.of(2023, 1, 1, 0, 0))
            .endDate(LocalDateTime.of(2023, 1, 2, 0, 0))
            .coords(List.of(Coords.builder().latitude(0).longitude(0).build()))
            .maxCapacity(1)
            .attendees(List.of(User.builder().id("2").build()))
            .build();


    @Test
    public void testCreateEvent() {
        when(eventPort.createEvent(testEvent)).thenReturn(testEvent);
        Event result = eventService.createEvent(testEvent);
        assertNotNull(result);
        assertEquals(testEvent, result);
        verify(eventPort, times(1)).createEvent(testEvent);
    }

    @Test
    public void testGetEventById() {
        when(eventPort.getEventById("1")).thenReturn(testEvent);
        Event result = eventService.getEventById("1");
        assertNotNull(result);
        assertEquals(testEvent, result);
        verify(eventPort, times(1)).getEventById("1");
    }

    @Test
    public void testdeleteEvent() {
        when(eventPort.getEventById("1")).thenReturn(testEvent);
        doNothing().when(eventPort).deleteEvent("1");
        eventService.deleteEvent("1");
        verify(eventPort, times(1)).deleteEvent("1");

    }

    @Test
    public void testDeleteEventDoesNotExist() throws Exception {
        when(eventPort.getEventById("1")).thenReturn(null);
        assertThrows(NotFoundException.class, () -> eventService.deleteEvent("1"));
        verify(eventPort, times(1)).getEventById("1");
        verifyNoInteractions(userPort);
    }

    @Test
    public void testGetEventByFestivityId() throws Exception {
        when(festivityPort.getById("1")).thenReturn(Festivity.builder().id("1").events(List.of(testEvent)).build());

        List<Event> result = eventService.getEventByFestivityId("1");
        assertNotNull(result);
        assertEquals(testEvent, result.getFirst());
        verify(festivityPort, times(1)).getById("1");
    }

    @Test
    public void testGetEventsByUserId() throws Exception {
        when(eventPort.getEventByUserId("1")).thenReturn(List.of(testEvent));

        List<Event> result = eventService.getEventsByUserId("1");
        assertNotNull(result);
        assertEquals(testEvent, result.get(0));
        verify(eventPort, times(2)).getEventByUserId("1");
    }

    @Test
    public void testGetEventsByUserIdDoesNotExist() throws Exception {
        when(eventPort.getEventByUserId("1")).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            eventService.getEventsByUserId("1");
        });

        assertEquals("Event not found", exception.getMessage());
        verify(eventPort, times(1)).getEventByUserId("1");
        verifyNoInteractions(userPort);
    }

    @Test
    public void testGetEventsByIdsList() throws Exception {
        when(eventPort.getEventByIdsList(List.of("1"))).thenReturn(List.of(testEvent));
        List<Event> result = eventService.getEventsByIdsList(List.of("1"));
        assertNotNull(result);
        assertEquals(testEvent, result.get(0));
        verify(eventPort, times(1)).getEventByIdsList(List.of("1"));
    }

    @Test
    public void testGetEventsByIdsListDoesNotExist() throws Exception {
        when(eventPort.getEventByIdsList(List.of("1"))).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            eventService.getEventsByIdsList(List.of("1"));
        });

        assertEquals("Event not found", exception.getMessage());
        verify(eventPort, times(1)).getEventByIdsList(List.of("1"));
    }

    @Test
    public void subscribeEvent() throws Exception {
        when(eventPort.getEventById("1")).thenReturn(testEvent);
        when(userPort.getUserById("1")).thenReturn(User.builder().id("1").build());
        doNothing().when(eventPort).updateEvent("1", testEvent);
        eventService.suscribeOrUnsuscribeEvent("1", "1");
        verify(eventPort, times(1)).getEventById("1");
        verify(userPort, times(1)).getUserById("1");
        verify(eventPort, times(1)).updateEvent("1", testEvent);
        assertEquals(testEvent, eventService.getEventById("1"));
        assertEquals(testEvent.getAttendees().size(), 1);
    }

    @Test
    public void unsubscribeEvent() throws Exception {
        when(eventPort.getEventById("1")).thenReturn(testEvent2);
        when(userPort.getUserById("1")).thenReturn(User.builder().id("1").build());
        doNothing().when(eventPort).updateEvent("1", testEvent2);
        eventService.suscribeOrUnsuscribeEvent("1", "1");
        verify(eventPort, times(1)).getEventById("1");
        verify(userPort, times(1)).getUserById("1");
        verify(eventPort, times(1)).updateEvent("1", testEvent2);
        assertEquals(testEvent2, eventService.getEventById("1"));
        assertEquals(testEvent2.getAttendees().size(), 0);
    }

    @Test
    public void suscribeEventFull() throws Exception {
        when(eventPort.getEventById("1")).thenReturn(testEvent2Full);
        when(userPort.getUserById("1")).thenReturn(User.builder().id("1").build());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            eventService.suscribeOrUnsuscribeEvent("1", "1");
        });


        verify(eventPort, times(1)).getEventById("1");
        verify(userPort, times(1)).getUserById("1");
        assertEquals("Event is full", exception.getMessage());
    }

    @Test
    public void suscribeEventUserDoesNotExist() throws Exception {
        when(eventPort.getEventById("1")).thenReturn(testEvent2Full);
        when(userPort.getUserById("1")).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            eventService.suscribeOrUnsuscribeEvent("1", "1");
        });


        verify(eventPort, times(1)).getEventById("1");
        verify(userPort, times(1)).getUserById("1");
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void suscribeEventEventDoesNotExist() throws Exception {
        when(eventPort.getEventById("1")).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            eventService.suscribeOrUnsuscribeEvent("1", "1");
        });

        verify(eventPort, times(1)).getEventById("1");
        assertEquals("Event not found", exception.getMessage());
    }


}
