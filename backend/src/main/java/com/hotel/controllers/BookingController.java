package com.hotel.controllers;

import com.hotel.models.Booking;
import com.hotel.models.Room;
import com.hotel.repositories.BookingRepository;
import com.hotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        if (booking.getRoom() != null) {
            Room room = roomRepository.findById(booking.getRoom().getId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));
            room.setStatus("OCCUPIED");
            room.setIsAvailable(false);
            roomRepository.save(room);
            booking.setRoom(room);
        }
        booking.setStatus("Active");
        return bookingRepository.save(booking);
    }

    @PostMapping("/{id}/checkout")
    public Booking checkOut(@PathVariable Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus("Completed");
        
        if (booking.getRoom() != null) {
            Room room = booking.getRoom();
            room.setStatus("DIRTY");
            room.setIsAvailable(false);
            roomRepository.save(room);
        }
        return bookingRepository.save(booking);
    }
}
