package com.example.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HotelIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fullFlowTest() throws Exception {
        mockMvc.perform(post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name":"Turtle",
                      "address":"TVM",
                      "phone": "9876543210",
                      "email": "turtle@example.com"
                    }
                """))
            .andExpect(status().isOk());

        mockMvc.perform(get("/hotels"))
            .andExpect(status().isOk());
    }
}