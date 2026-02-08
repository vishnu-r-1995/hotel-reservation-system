package com.example.reservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.reservation.job.RoomTypeInventoryJobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/jobs")
@RequiredArgsConstructor
public class InventoryJobController {

    private final RoomTypeInventoryJobService jobService;

    @PostMapping("/populate-inventory")
    public ResponseEntity<String> triggerInventoryJob() {
        jobService.populateRoomTypeInventory();
        return ResponseEntity.ok("Room type inventory job triggered successfully");
    }
}
