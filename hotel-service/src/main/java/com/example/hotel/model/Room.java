package com.example.hotel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
    name = "rooms",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"hotel_id", "room_number"}
    )
)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long hotelId;
    private String roomNumber;
    private Long roomTypeId;
    private Double price;
    private Boolean isAvailable;
}
