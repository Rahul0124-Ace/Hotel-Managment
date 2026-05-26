package com.hotel.controllers;

import com.hotel.models.Booking;
import com.hotel.models.Room;
import com.hotel.models.RoomServiceOrder;
import com.hotel.repositories.RoomRepository;
import com.hotel.repositories.RoomServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class RoomServiceOrderController {

    @Autowired
    private RoomServiceOrderRepository orderRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<RoomServiceOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public RoomServiceOrder createOrder(@RequestBody RoomServiceOrder order) {
        return orderRepository.save(order);
    }

    @PutMapping("/{id}/status")
    public RoomServiceOrder updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        RoomServiceOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        
        if ("DELIVERED".equalsIgnoreCase(status) && "Room Cleaning".equalsIgnoreCase(order.getDescription())) {
            Booking booking = order.getBooking();
            if (booking != null && booking.getRoom() != null) {
                Room room = booking.getRoom();
                room.setStatus("AVAILABLE");
                room.setIsAvailable(true);
                roomRepository.save(room);
            }
        }
        return orderRepository.save(order);
    }
}
