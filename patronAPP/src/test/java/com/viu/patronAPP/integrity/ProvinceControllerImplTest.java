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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class ProvinceControllerImplTest {

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
    void testGetProvinces() throws Exception {
        mockMvc.perform(get("/provinces")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("A Coruña"))
                .andExpect(jsonPath("$[0].autonomousCommunity").value("Galicia"))
                .andExpect(jsonPath("$[0].coords.latitude").value(43.3623));
    }

    @Test
    void testGetVillagesByProvince() throws Exception {
        mockMvc.perform(get("/provinces/{provinceId}/villages", "49")
                        .param("page", "1")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Castronuño"))
                .andExpect(jsonPath("$[0].provinceId").value("49"));
    }

    @Test
    void testGetVillagesByProvincePageSize() throws Exception {
        mockMvc.perform(get("/provinces/{provinceId}/villages/paginated", "49")
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Castronuño"))
                .andExpect(jsonPath("$[0].provinceId").value("49"));
    }

    @Test
    void testGetAllVillagesByProvince() throws Exception {
        mockMvc.perform(get("/provinces/{provinceId}/villages", "49")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Castronuño"))
                .andExpect(jsonPath("$[0].provinceId").value("49"));
    }


    @Test
    void testGetVillagesByProvinceBadRequest() throws Exception {
        mockMvc.perform(get("/provinces/{provinceId}/villages/paginated", "1")
                        .param("page", "badValue")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
