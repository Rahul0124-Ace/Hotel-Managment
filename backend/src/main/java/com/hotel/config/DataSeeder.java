package com.hotel.config;

import com.hotel.models.Room;
import com.hotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roomRepository.count() == 0) {
            // First Floor: Rooms 101, 102, 103, 104, 105 (Standard Rooms - $100/night)
            for (int i = 1; i <= 5; i++) {
                Room r = new Room();
                r.setRoomNumber("10" + i);
                r.setType("Standard Room");
                r.setPricePerNight(100.0);
                r.setIsAvailable(true);
                r.setStatus("AVAILABLE");
                roomRepository.save(r);
            }
            // Second Floor: Rooms 201, 202, 203, 204, 205 (Deluxe Suites - $200/night)
            for (int i = 1; i <= 5; i++) {
                Room r = new Room();
                r.setRoomNumber("20" + i);
                r.setType("Deluxe Suite");
                r.setPricePerNight(200.0);
                r.setIsAvailable(true);
                r.setStatus("AVAILABLE");
                roomRepository.save(r);
            }
            System.out.println("Seeded exactly 10 rooms into the database.");
        }
    }
}
