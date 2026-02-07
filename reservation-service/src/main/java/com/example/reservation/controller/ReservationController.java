package com.example.reservation.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.reservation.model.Reservation;
import com.example.reservation.service.ReservationService;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(
            @RequestBody ReservationRequest request
    ) {
        Reservation reservation = service.createReservation(request);
        return ResponseEntity.ok(reservation);
    }


    @GetMapping("/{reservationId}")
    public Reservation getReservation(@PathVariable Long reservationId) {
        return service.getReservationById(reservationId);
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