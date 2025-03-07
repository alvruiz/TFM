package com.viu.patronAPP.infrastructure.in.web.controller.impl.user.impl;

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
class UserControllerImplTest {

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
    void testGetUserByEmail() throws Exception {
        mockMvc.perform(get("/user/admin@admin.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("admin"))
                .andExpect(jsonPath("$.surname").value("admin"))
                .andExpect(jsonPath("$.email").value("admin@admin.com"))
                .andExpect(jsonPath("$.age").value(40))
                .andExpect(jsonPath("$.gender").value("M"))
                .andExpect(jsonPath("$.rol").value("ADMIN"))
                .andExpect(jsonPath("$.imageUrl").value("https://store-images.s-microsoft.com/image/apps.31920.f265099d-4c71-493f-b32c-b66015371675.41795bd4-3595-4260-808a-d3feb5d18d45.5b70495e-924a-49e6-a2cd-85d3bdfa781d.png"));
    }

    @Test
    void testCreateUser() throws Exception {
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"admin\",\"surname\":\"admin\",\"email\":\"admin2@admin.com\",\"password\":\"123456\",\"age\":40,\"gender\":\"M\",\"imageUrl\":\"https://store-images.s-microsoft.com/image/apps.31920.f265099d-4c71-493f-b32c-b66015371675.41795bd4-3595-4260-808a-d3feb5d18d45.5b70495e-924a-49e6-a2cd-85d3bdfa781d.png\",\"eventsParticipating\":[\"1\",\"2\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("admin2@admin.com"))
                .andExpect(jsonPath("$.name").value("admin"))
                .andExpect(jsonPath("$.surname").value("admin"));
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"admin@admin.com\",\"password\":\"adminadmin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("admin@admin.com"))
                .andExpect(jsonPath("$.name").value("admin"))
                .andExpect(jsonPath("$.surname").value("admin"));
    }

    @Test
    void testUpdateUser() throws Exception {
        mockMvc.perform(put("/user")
                        .contentType("application/json")
                        .content("{\"name\":\"admin\",\"surname\":\"admin\",\"email\":\"admin@admin.com\",\"password\":\"123456\",\"age\":40,\"gender\":\"M\",\"rol\":\"ADMIN\",\"imageUrl\":\"https://store-images.s-microsoft.com/image/apps.31920.f265099d-4c71-493f-b32c-b66015371675.41795bd4-3595-4260-808a-d3feb5d18d45.5b70495e-924a-49e6-a2cd-85d3bdfa781d.png\",\"eventsParticipating\":[\"1\",\"2\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("admin@admin.com"))
                .andExpect(jsonPath("$.name").value("admin"));
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }

    @Test
    void testDeleteUserNotFound() throws Exception {
        mockMvc.perform(delete("/user/nonexistent@admin.com"))
                .andExpect(status().isNotFound());
    }
}
