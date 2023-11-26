package ybe.mini.travelserver.domain.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final TourAPIService tourAPIService;

    @Transactional(readOnly = true)
    public List<RoomGetResponse> bringRooms(Long accommodationId) {
        return roomRepository.findByAccommodationId(accommodationId)
                .stream()
                .map(RoomGetResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public RoomGetResponse bringRoom(Long accommodationId, Long roomId) {
        Room room = tourAPIService.bringRoom(accommodationId, roomId);
        return RoomGetResponse.fromEntity(room);
    }

    @Transactional(readOnly = true)
    public RoomGetResponse bringRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(RuntimeException::new);
        return RoomGetResponse.fromEntity(room);
    }


}
