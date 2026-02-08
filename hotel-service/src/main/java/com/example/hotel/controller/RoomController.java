package com.example.hotel.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.hotel.dto.RoomDTO;
import com.example.hotel.service.RoomService;
import com.example.hotel.model.Room;

@RestController
@RequestMapping("/hotels/{hotelId}/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping
    public Room createRoom(
            @PathVariable Long hotelId,
            @RequestBody Room room) {
        return service.createRoom(hotelId, room);
    }

    // @GetMapping
    // public List<Room> getRooms(@PathVariable Long hotelId) {
    //     return service.getActiveRooms(hotelId);
    // }

    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return service.getAvailableRooms();
    }
}