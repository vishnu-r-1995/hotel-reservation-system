package com.example.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import com.example.reservation.model.RoomTypeInventory;

public interface RoomTypeInventoryRepository
        extends JpaRepository<RoomTypeInventory, Long> {

    List<RoomTypeInventory> findByHotelIdAndRoomTypeIdAndDateBetween(
            Long hotelId,
            Long roomTypeId,
            LocalDate start,
            LocalDate end
    );
}