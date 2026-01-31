package com.example.hotel;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelController.class)
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @Test
    void shouldCreateHotel() throws Exception {
        //Stub service behavior
        when(hotelService.create(any(Hotel.class)))
                .thenReturn(new Hotel(
                        1L,
                        "Turtle",
                        "TVM",
                        "9876543210",
                        "h@gmail.com"
                ));

        mockMvc.perform(post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name":"Turtle",
                      "address":"TVM",
                      "phone": "9876543210",
                      "email": "h@gmail.com"
                    }
                """))
                .andExpect(status().isOk());
    }
}
