package com.hotel.repositories;

import com.hotel.models.RoomServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomServiceOrderRepository extends JpaRepository<RoomServiceOrder, Long> {
}
