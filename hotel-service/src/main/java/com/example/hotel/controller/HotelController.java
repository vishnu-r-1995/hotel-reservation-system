package com.example.hotel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hotel.service.HotelService;
import com.example.hotel.model.Hotel;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {
        return service.create(hotel);
    }

    @GetMapping("/{id}")
    public Hotel get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<Hotel> all() {
        return service.getAll();
    }
}