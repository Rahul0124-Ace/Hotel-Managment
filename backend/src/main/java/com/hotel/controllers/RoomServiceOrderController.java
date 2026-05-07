package com.hotel.controllers;

import com.hotel.models.RoomServiceOrder;
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

    @GetMapping
    public List<RoomServiceOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public RoomServiceOrder createOrder(@RequestBody RoomServiceOrder order) {
        return orderRepository.save(order);
    }
}
