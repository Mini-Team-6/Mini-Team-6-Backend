package ybe.mini.travelserver.domain.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public List<RoomGetResponse> getAllRoomByAccommodation(Long accommodationId) {
        List<Room> rooms = roomRepository.findByAccommodationId(accommodationId);
        return rooms.stream()
                .map(RoomGetResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Room getRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(RuntimeException::new);
        return room;
    }
}
