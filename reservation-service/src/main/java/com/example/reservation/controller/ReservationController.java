package com.example.reservation.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservation.model.Reservation;
import com.example.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

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