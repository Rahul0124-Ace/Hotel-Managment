package com.hotel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String roomNumber;
    private String type; // e.g. Single, Double, Suite
    private Double pricePerNight;
    private Boolean isAvailable;
    private String status; // AVAILABLE, OCCUPIED, DIRTY
}
