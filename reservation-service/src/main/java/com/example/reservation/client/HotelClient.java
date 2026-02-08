package com.example.reservation.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.reservation.client.dto.RoomDTO;

@Service
public class HotelClient {

    private final RestTemplate restTemplate;

    public HotelClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RoomDTO> getAvailableRooms() {
        RoomDTO[] rooms = restTemplate.getForObject(
                //"http://hotel-service:8081/rooms",  //In docker
                "http://localhost:8081/rooms",
                RoomDTO[].class
        );

        return rooms != null ? Arrays.asList(rooms) : List.of();
    }
}
