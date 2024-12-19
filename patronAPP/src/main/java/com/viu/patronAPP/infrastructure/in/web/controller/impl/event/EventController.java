package com.viu.patronAPP.infrastructure.in.web.controller.impl.event;

import com.viu.patronAPP.infrastructure.DTO.event.EventAndVillageDTO;
import com.viu.patronAPP.infrastructure.DTO.event.EventDTO;
import com.viu.patronAPP.infrastructure.DTO.event.SubscribeDTO;
import com.viu.patronAPP.infrastructure.DTO.user.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/events")
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
    @PreAuthorize("hasAnyRole('ADMIN','CM')")
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
    @CrossOrigin
    public ResponseEntity<UserDTO> subscribeOrUnsubscribeEvent(@RequestBody SubscribeDTO suscribeDTO);

    @Operation(
            summary = "Get an event by festivity ID",
            description = "This endpoint retrieves an event by its festivity ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Event not found")
            }
    )
    @GetMapping("/{festivityId}")
    @CrossOrigin
    public ResponseEntity<List<EventDTO>> getEventByFestivityId(@PathVariable String festivityId);

    @Operation(
            summary = "Get an event by user ID",
            description = "This endpoint retrieves an event by its user ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Event not found")
            }
    )
    @GetMapping("/user/{userId}")
    @CrossOrigin
    public ResponseEntity<List<EventAndVillageDTO>> getEventByUserId(@PathVariable String userId);


    @Operation(
            summary = "Get an event by festivity ID",
            description = "This endpoint retrieves an event by user email.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Event not found")
            }
    )
    @GetMapping("/email/{email}")
    @CrossOrigin
    public ResponseEntity<List<EventAndVillageDTO>> getEventsByEmail(@PathVariable String email);

    @PostMapping("/list")
    @CrossOrigin
    public ResponseEntity<List<EventAndVillageDTO>> getEventsByIDsList(@RequestBody List<String> ids);
}
