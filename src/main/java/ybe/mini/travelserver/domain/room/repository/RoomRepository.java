package ybe.mini.travelserver.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ybe.mini.travelserver.domain.room.entity.Room;

import java.util.List;
import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAccommodationId(Long accommodationId);

    Optional<Room> findByRoomTypeId(Long roomTypeId);
}
