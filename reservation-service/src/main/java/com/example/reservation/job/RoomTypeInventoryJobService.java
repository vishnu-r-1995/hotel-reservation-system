package com.example.reservation.job;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservation.client.HotelClient;
import com.example.reservation.client.dto.RoomDTO;
import com.example.reservation.model.RoomTypeInventory;
import com.example.reservation.repository.RoomTypeInventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomTypeInventoryJobService {

    private static final int DAYS_TO_POPULATE = 30;

    private final HotelClient hotelClient;
    private final RoomTypeInventoryRepository inventoryRepository;

    @Transactional
    public void populateRoomTypeInventory() {

        List<RoomDTO> rooms = hotelClient.getAvailableRooms();

        Map<InventoryKey, Long> inventoryCount =
                rooms.stream()
                     .filter(RoomDTO::isAvailable)
                     .collect(Collectors.groupingBy(
                             r -> new InventoryKey(r.hotelId(), r.roomTypeId()),
                             Collectors.counting()
                     ));

        LocalDate today = LocalDate.now();

        for (int i = 0; i < DAYS_TO_POPULATE; i++) {
            LocalDate date = today.plusDays(i);

            for (Map.Entry<InventoryKey, Long> entry : inventoryCount.entrySet()) {

                InventoryKey key = entry.getKey();
                int totalInventory = entry.getValue().intValue();

                boolean exists =
                        inventoryRepository.existsByHotelIdAndRoomTypeIdAndDate(
                                key.hotelId(),
                                key.roomTypeId(),
                                date
                        );

                if (!exists) {
                    RoomTypeInventory inventory =
                            RoomTypeInventory.builder()
                                    .hotelId(key.hotelId())
                                    .roomTypeId(key.roomTypeId())
                                    .date(date)
                                    .totalInventory(totalInventory)
                                    .totalReserved(0)
                                    .build();

                    inventoryRepository.save(inventory);
                }
            }
        }
    }

    record InventoryKey(Long hotelId, Long roomTypeId) {}
}
