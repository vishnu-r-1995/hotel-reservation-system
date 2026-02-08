package com.example.hotel.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.hotel.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    //List<Room> findByHotelIdAndActiveTrue(Long hotelId);
    List<Room> findByIsAvailableTrue();
}