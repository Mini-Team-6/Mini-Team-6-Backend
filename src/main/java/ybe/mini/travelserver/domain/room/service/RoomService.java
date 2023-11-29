package ybe.mini.travelserver.domain.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;
import ybe.mini.travelserver.domain.reservation_room.repository.ReservationRoomRepository;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponseFromAPI;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;
import ybe.mini.travelserver.global.api.TourAPIService;
import ybe.mini.travelserver.global.util.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ybe.mini.travelserver.global.util.Validation.validateDateFormat;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final TourAPIService tourAPIService;
    private final ReservationRoomRepository reservationRoomRepository;
    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public List<RoomGetResponseFromAPI> bringRoomsFromAPI(Long accommodationId, String checkIn, String checkOut) {
        List<Room> rooms = tourAPIService.bringRooms(accommodationId);

        return rooms.stream()
                .map(room -> RoomGetResponseFromAPI.fromEntity(
                        room, getRestStock(room, validateDateFormat(checkIn), validateDateFormat(checkOut))
                )).toList();
    }

    private Integer getRestStock(Room room, LocalDate checkIn, LocalDate checkOut) {
        Optional<Room> roomOpt = roomRepository.findByRoomTypeId(room.getRoomTypeId());
        if(roomOpt.isEmpty()) return room.getStock();

        List<ReservationRoom> reservationRooms =
                reservationRoomRepository.findAllByRoomAndCheckInBetweenAndCheckOutBetween(
                    roomOpt.get(), checkIn, checkOut, checkIn, checkOut
                );
        return Math.max(0, room.getStock() - reservationRooms.size());
    }

}
