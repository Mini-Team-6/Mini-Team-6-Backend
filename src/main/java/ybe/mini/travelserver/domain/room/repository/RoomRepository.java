package ybe.mini.travelserver.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.room.entity.Room;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAccommodationId(Long accommodationId);
}
