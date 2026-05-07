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
            for (int i = 1; i <= 5; i++) {
                Room r = new Room();
                r.setRoomNumber("10" + i);
                r.setType("Single");
                r.setPricePerNight(100.0);
                r.setIsAvailable(true);
                roomRepository.save(r);
            }
            for (int i = 1; i <= 3; i++) {
                Room r = new Room();
                r.setRoomNumber("20" + i);
                r.setType("Double");
                r.setPricePerNight(150.0);
                r.setIsAvailable(true);
                roomRepository.save(r);
            }
            for (int i = 1; i <= 2; i++) {
                Room r = new Room();
                r.setRoomNumber("30" + i);
                r.setType("Suite");
                r.setPricePerNight(250.0);
                r.setIsAvailable(true);
                roomRepository.save(r);
            }
            System.out.println("Seeded 10 rooms into the database.");
        }
    }
}
