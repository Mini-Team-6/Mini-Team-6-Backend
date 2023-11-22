package ybe.mini.travelserver.domain.reservation_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;

public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {
}
