package com.example.hotel.dto;

public record RoomDTO(
        Long hotelId,
        Long roomTypeId,
        boolean isAvailable
) {}
