package com.example.reservation.client.dto;

public record RoomDTO(
        Long hotelId,
        Long roomTypeId,
        boolean isAvailable
) {}