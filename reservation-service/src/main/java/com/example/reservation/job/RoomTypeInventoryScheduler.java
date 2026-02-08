package com.example.reservation.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomTypeInventoryScheduler {

    private final RoomTypeInventoryJobService jobService;

    @Scheduled(cron = "0 0 1 * * ?") // daily at 1 AM
    public void runScheduledInventoryJob() {
        jobService.populateRoomTypeInventory();
    }
}