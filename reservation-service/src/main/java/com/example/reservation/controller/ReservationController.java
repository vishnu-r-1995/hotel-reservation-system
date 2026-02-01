package com.example.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    public Reservation create(@RequestBody ReservationRequest req) {
        return service.createReservation(
                req.hotelId(),
                req.roomTypeId(),
                req.startDate(),
                req.endDate(),
                req.guestId()
        );
    }
}

record ReservationRequest(
        Long hotelId,
        Long roomTypeId,
        LocalDate startDate,
        LocalDate endDate,
        Long guestId
) {}