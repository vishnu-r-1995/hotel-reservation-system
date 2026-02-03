package com.example.reservation.metrics;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;

@Component
public class ReservationMetrics {

    private final MeterRegistry registry;

    public ReservationMetrics(MeterRegistry registry) {
        this.registry = registry;
    }

    public void reservationCreated() {
        registry.counter("reservation.created.count").increment();
    }

    public void inventoryExhausted() {
        registry.counter("reservation.inventory.exhausted.count").increment();
    }
}