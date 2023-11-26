package ybe.mini.travelserver.domain.reservation_room.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record ReservationRoomGetResponse(
        Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime checkIn,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime checkOut,
        Integer guestNumber,
        ReservationRoomStatus status,
        RoomGetResponse room    //Todo : Room room 으로 변경 논의

) implements Serializable {

        public static ReservationRoomGetResponse fromEntity(ReservationRoom reservationRoom) {
                return ReservationRoomGetResponse.builder()
                        .id(reservationRoom.getId())
                        .room(RoomGetResponse.fromEntity(reservationRoom.getRoom()))
                        .checkIn(reservationRoom.getCheckIn())
                        .checkOut(reservationRoom.getCheckOut())
                        .guestNumber(reservationRoom.getGuestNumber())
                        .status(reservationRoom.getStatus())
                        .build();
        }
}
