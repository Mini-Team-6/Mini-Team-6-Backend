package ybe.mini.travelserver.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ybe.mini.travelserver.domain.room.entity.Room;

import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomTypeId(Long roomTypeId);
}
