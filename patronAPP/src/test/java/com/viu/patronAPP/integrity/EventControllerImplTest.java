package com.viu.patronAPP.integrity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class EventControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ApplicationContext context;


    @BeforeEach
    void setup() throws Exception {
        CommandLineRunner runner = (CommandLineRunner) context.getBean("loadVillages");
        runner.run();
    }

    @Test
    void testCreateEvent() throws Exception {
        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"eventName\":\"Concierto de Folk\",\"eventDescription\":\"Espectáculo musical al aire libre con artistas locales.\",\"eventStartDate\":\"2025-09-19T16:00:00\",\"eventEndDate\":\"2025-09-19T18:00:00\",\"coords\":[{\"latitude\":41.389710,\"longitude\":-5.263901}],\"eventMaxCapacity\":150,\"attendees\":[\"1\"],\"eventFestivityId\":\"2\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventName").value("Concierto de Folk"))
                .andExpect(jsonPath("$.coords[0].latitude").value(41.389710))
                .andExpect(jsonPath("$.coords[0].longitude").value(-5.263901));
    }

    @Test
    void testDeleteEvent() throws Exception {
        mockMvc.perform(delete("/events/{eventId}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }

    @Test
    void testSubscribeOrUnsubscribeEvent() throws Exception {
        mockMvc.perform(post("/events/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"admin@admin.com\",\"eventId\":\"1\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEventByFestivityId() throws Exception {
        mockMvc.perform(get("/events/{festivityId}", "7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventName").value("Concierto de Folk"));
    }

    @Test
    void testGetEventByUserId() throws Exception {
        mockMvc.perform(get("/events/user/{userId}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEventsByEmail() throws Exception {
        mockMvc.perform(get("/events/email/{email}", "admin@admin.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventName").value("Concierto de Folk"));
    }

    @Test
    void testGetEventsByIDsList() throws Exception {
        mockMvc.perform(post("/events/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"1\", \"2\", \"3\"]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventName").value("Concierto de Folk"));
    }
}
