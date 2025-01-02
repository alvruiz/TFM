package com.viu.patronAPP.integrity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VillageControllerImplTest {

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
    void testGetVillageById() throws Exception {
        mockMvc.perform(get("/villages/{villageId}", "4")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("4"))
                .andExpect(jsonPath("$.name").value("Villaobispo De Otero"));
    }

    @Test
    void testCreateVillage() throws Exception {
        mockMvc.perform(post("/villages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"village2\",\"name\":\"Village 2\",\"coords\":{\"latitude\":2, \"longitude\":4},\"imageUrl\":\"https://example.com/image.jpg\",\"provinceId\":\"1\",\"festivity\":{\"id\":\"festivity1\",\"name\":\"Festivity 1\",\"startDate\":\"2025-01-01\",\"endDate\":\"2025-01-05\",\"patron\":\"Patron 1\"}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Village 2"));
    }

    @Test
    void testUpdateVillage() throws Exception {
        mockMvc.perform(put("/villages/{villageId}", "3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"village1\",\"name\":\"Updated Village 1\",\"coords\":{\"latitude\":2, \"longitude\":4},\"imageUrl\":\"https://example.com/updated_image.jpg\",\"provinceId\":\"province1\",\"festivity\":{\"id\":\"festivity1\",\"name\":\"Updated Festivity\",\"startDate\":\"2025-02-01\",\"endDate\":\"2025-02-05\",\"patron\":\"Updated Patron\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Village 1"));
    }

    @Test
    void testDeleteVillage() throws Exception {
        mockMvc.perform(delete("/villages/{villageId}", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }

    @Test
    void testDeleteVillageNotFound() throws Exception {
        mockMvc.perform(delete("/villages/{villageId}", "nonexistentVillage"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllVillages() throws Exception {
        mockMvc.perform(get("/villages")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

}
