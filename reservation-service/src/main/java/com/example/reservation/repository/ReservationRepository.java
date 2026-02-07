package com.example.reservation.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reservation.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByReservationId(String reservationId);
}
