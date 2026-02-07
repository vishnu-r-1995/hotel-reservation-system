package com.example.reservation.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(
    name = "reservation",
    uniqueConstraints = @UniqueConstraint(columnNames = "reservation_id")
)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Idempotency key (client-generated)
    @Column(nullable = false, unique = true)
    private String reservationId;

    private Long hotelId;
    private Long roomTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long guestId;
    private String status;
}
