package com.example.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hotel.model.Hotel;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.service.HotelService;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    void shouldCreateHotel() {
        Hotel hotel = new Hotel(null, "Turtle", "TVM", "1234567890", "h@gmail.com", "Trivandrum");
        when(hotelRepository.save(any())).thenReturn(hotel);

        Hotel saved = hotelService.create(hotel);

        assertEquals("Turtle", saved.getName());
        verify(hotelRepository).save(any());
    }
}
