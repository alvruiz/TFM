package com.viu.patronAPP.infrastructure.in.web.controller.impl.event;

import com.viu.patronAPP.infrastructure.DTO.event.EventDTO;
import com.viu.patronAPP.infrastructure.DTO.event.SubscribeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/event")
@Tag(name = "Event", description = "Operations related to event creation, subscription, and management.")
public interface EventController {
    @Operation(
            summary = "Create a new event",
            description = "This endpoint allows the creation of a new event by providing event details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PostMapping("/")
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO);

    @Operation(
            summary = "Delete an event",
            description = "This endpoint allows the deletion of an event by its unique ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Event not found")
            }
    )
    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable String eventId);

    @Operation(
            summary = "Subscribe or unsubscribe from an event",
            description = "This endpoint allows a user to subscribe or unsubscribe from an event.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operation successful"),
                    @ApiResponse(responseCode = "404", description = "Event not found"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "400", description = "Event is full")
            }
    )
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeOrUnsubscribeEvent(@RequestBody SubscribeDTO suscribeDTO);
}
