package com.example.reservation.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(
    name = "room_type_inventory",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"hotel_id", "room_type_id", "date"}
    )
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class RoomTypeInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long hotelId;
    private Long roomTypeId;

    private LocalDate date;

    private int totalInventory;
    private int totalReserved;
}