package com.example.reservation.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservation.metrics.ReservationMetrics;
import com.example.reservation.model.Reservation;
import com.example.reservation.model.RoomTypeInventory;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.repository.RoomTypeInventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomTypeInventoryRepository inventoryRepository;
    private final ReservationMetrics metrics;

    @Transactional
    public Reservation createReservation(
            Long hotelId,
            Long roomTypeId,
            LocalDate start,
            LocalDate end,
            Long guestId
    ) {
        List<RoomTypeInventory> inventory =
                inventoryRepository.findByHotelIdAndRoomTypeIdAndDateBetween(
                        hotelId, roomTypeId, start, end.minusDays(1)
                );

        for (RoomTypeInventory day : inventory) {
            if (day.getTotalReserved() >= day.getTotalInventory()) {
                metrics.inventoryExhausted();
                throw new IllegalStateException("No inventory available");
            }
        }

        // Reserve inventory
        inventory.forEach(day ->
                day.setTotalReserved(day.getTotalReserved() + 1)
        );

        Reservation reservation = Reservation.builder()
                .hotelId(hotelId)
                .roomTypeId(roomTypeId)
                .startDate(start)
                .endDate(end)
                .guestId(guestId)
                .status("BOOKED")
                .build();

        metrics.reservationCreated();
        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(()
                        -> new IllegalArgumentException("Reservation not found: " + reservationId)
                );
    }
}