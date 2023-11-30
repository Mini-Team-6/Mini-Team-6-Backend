package ybe.mini.travelserver.domain.reservation_room.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record ReservationRoomGetResponse(
        Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate checkIn,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate checkOut,
        Integer guestNumber,
        ReservationRoomStatus status,
        RoomGetResponse room,
        AccommodationGetResponse accommodation

) implements Serializable {

        public static ReservationRoomGetResponse fromEntity(ReservationRoom reservationRoom) {
                return ReservationRoomGetResponse.builder()
                        .id(reservationRoom.getId())
                        .room(RoomGetResponse.fromEntity(reservationRoom.getRoom()))
                        .accommodation(AccommodationGetResponse.fromEntity(reservationRoom.getRoom().getAccommodation()))
                        .checkIn(reservationRoom.getCheckIn())
                        .checkOut(reservationRoom.getCheckOut())
                        .guestNumber(reservationRoom.getGuestNumber())
                        .status(reservationRoom.getStatus())
                        .build();
        }
}
