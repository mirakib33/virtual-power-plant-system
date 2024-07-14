package com.bracit.virtualpowerplant.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BatteryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddBatteries() throws Exception {
        String batteriesJson = "[{\"name\":\"Battery1\",\"postcode\":\"12345\",\"wattCapacity\":100}," +
                "{\"name\":\"Battery2\",\"postcode\":\"23456\",\"wattCapacity\":200}," +
                "{\"name\":\"Battery3\",\"postcode\":\"34567\",\"wattCapacity\":150}]";

        mockMvc.perform(MockMvcRequestBuilders.post("/batteries")
                        .contentType("application/json")
                        .content(batteriesJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Battery1"));
    }

    @Test
    public void testGetBatteriesInRange() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/batteries")
                        .param("postcodeRange", "20000-30000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("Battery2"));
    }

}
