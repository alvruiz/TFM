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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class FestivityControllerImplTest {

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
    void testCreateFestivity() throws Exception {
        String festivityJson = """
                {
                  "id": "111111",
                  "name": "Spring Festival",
                  "startDate": "2025-03-21",
                  "endDate": "2025-03-22",
                  "patron": "Saint John",
                  "villageId": "Village1"
                }
                """;

        mockMvc.perform(post("/festivities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(festivityJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Spring Festival"))
                .andExpect(jsonPath("$.patron").value("Saint John"));
    }

    @Test
    void testGetFestivityByVillageId() throws Exception {
        mockMvc.perform(get("/festivities/villages/{villageId}", "7")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.villageId").value("7"))
                .andExpect(jsonPath("$.name").value("Fiesta Patronal San Pedro"));
    }

    @Test
    void testGetFestivities() throws Exception {
        mockMvc.perform(get("/festivities")
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    void testGetFestivityById() throws Exception {
        mockMvc.perform(get("/festivities/{festivityId}", "7")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("7"))
                .andExpect(jsonPath("$.name").value("Fiesta Patronal San José"));
    }

    @Test
    void testUpdateFestivity() throws Exception {
        String updatedFestivityJson = """
                {
                  "id": "1",
                  "name": "Updated Festivity",
                  "startDate": "2025-03-21",
                  "endDate": "2025-03-22",
                  "patron": "Updated Saint",
                  "villageId": "Village1"
                }
                """;

        mockMvc.perform(put("/festivities/{festivityId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedFestivityJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Festivity"))
                .andExpect(jsonPath("$.patron").value("Updated Saint"));
    }

    @Test
    void testBadRequestOnInvalidData() throws Exception {
        String invalidFestivityJson = """
                {
                  "id": "",
                  "name": "",
                  "startDate": "invalid-date",
                  "endDate": "2025-03-22",
                  "patron": "",
                  "villageId": "Village1"
                }
                """;

        mockMvc.perform(post("/festivities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidFestivityJson))
                .andExpect(status().isInternalServerError());
    }
}
