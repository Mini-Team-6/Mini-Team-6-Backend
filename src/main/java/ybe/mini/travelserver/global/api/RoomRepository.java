package ybe.mini.travelserver.global.api;

import org.springframework.data.jpa.repository.JpaRepository;
import ybe.mini.travelserver.domain.room.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
