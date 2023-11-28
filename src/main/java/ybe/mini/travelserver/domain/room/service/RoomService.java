package ybe.mini.travelserver.domain.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponseFromAPI;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final TourAPIService tourAPIService;

    public List<RoomGetResponseFromAPI> bringRoomsFromAPI(Long accommodationId) {
        List<Room> rooms = tourAPIService.bringRooms(accommodationId);
        return rooms.stream()
                        .map(RoomGetResponseFromAPI::fromEntity)
                        .toList();
    }

}
