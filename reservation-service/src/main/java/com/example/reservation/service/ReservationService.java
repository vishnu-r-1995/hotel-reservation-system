package com.example.reservation.service;

import java.util.*;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservation.metrics.ReservationMetrics;
import com.example.reservation.model.Reservation;
import com.example.reservation.model.RoomTypeInventory;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.repository.RoomTypeInventoryRepository;
import org.springframework.dao.DataIntegrityViolationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomTypeInventoryRepository inventoryRepository;
    private final ReservationMetrics metrics;

    @Transactional
    public Reservation createReservation(ReservationRequest request) {
        Optional<Reservation> existing
                = reservationRepository.findByReservationId(request.reservationId());

        if (existing.isPresent()) {
            return existing.get();
        }

        List<RoomTypeInventory> inventory =
                inventoryRepository.findByHotelIdAndRoomTypeIdAndDateBetween(
                        request.hotelId(), request.roomTypeId(), request.startDate(), request.endDate().minusDays(1)
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
                .reservationId(request.reservationId())
                .hotelId(request.hotelId())
                .roomTypeId(request.roomTypeId())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .guestId(request.guestId())
                .status("BOOKED")
                .build();

        metrics.reservationCreated();
        try {
            return reservationRepository.save(reservation);
        } catch (DataIntegrityViolationException e) {
            // Another request beat us
            return reservationRepository
                    .findByReservationId(request.reservationId())
                    .orElseThrow();
        }
    }

    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(()
                        -> new IllegalArgumentException("Reservation not found: " + reservationId)
                );
    }
}

record ReservationRequest(
        String reservationId, 
        Long hotelId,
        Long roomTypeId,
        LocalDate startDate,
        LocalDate endDate,
        Long guestId
) {}