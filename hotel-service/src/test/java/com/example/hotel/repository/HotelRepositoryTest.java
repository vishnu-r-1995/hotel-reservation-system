package com.example.hotel;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.hotel.model.Hotel;
import com.example.hotel.repository.HotelRepository;

@DataJpaTest
class HotelRepositoryTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    void shouldSaveHotel() {
        // given
        Hotel hotel = new Hotel(
                null,
                "Gokulam",
                "TVM",
                "9876543210",
                "gokulam@example.com",
                "Trivandrum"
        );

        // when
        Hotel saved = hotelRepository.save(hotel);

        // then
        assertNotNull(saved.getHotelId(), "Hotel ID should be generated");
        assertEquals("Gokulam", saved.getName());
    }
}