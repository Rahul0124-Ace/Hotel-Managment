package com.hotel.controllers;

import com.hotel.models.Room;
import com.hotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping("/{id}/clean")
    public Room cleanRoom(@PathVariable Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        room.setStatus("AVAILABLE");
        room.setIsAvailable(true);
        return roomRepository.save(room);
    }
}
