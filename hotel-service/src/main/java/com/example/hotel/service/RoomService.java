package com.example.hotel.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.dto.RoomDTO;
import com.example.hotel.model.Room;

@Service
public class RoomService {

    private final RoomRepository repository;
    private final HotelRepository hotelRepository;

    public RoomService(RoomRepository repository, HotelRepository hotelRepository) {
        this.repository = repository;
        this.hotelRepository = hotelRepository;
    }

    public Room createRoom(Long hotelId, Room room) {
        hotelRepository.findById(hotelId)
            .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        room.setHotelId(hotelId);
        room.setIsAvailable(true);
        return repository.save(room);
    }

    // public List<Room> getActiveRooms(Long hotelId) {
    //     return repository.findByHotelIdAndActiveTrue(hotelId);
    // }

    public List<RoomDTO> getAvailableRooms() {
        return repository.findByIsAvailableTrue()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private RoomDTO toDto(Room room) {
        return new RoomDTO(
                room.getHotelId(),
                room.getRoomTypeId(),
                Boolean.TRUE.equals(room.getIsAvailable())
        );
    }
}