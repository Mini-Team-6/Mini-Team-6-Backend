package ybe.mini.travelserver.domain.reservation_room.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ReservationRoomCreateRequest(

        Long accommodationId,
        String accommodationName,
        Long roomTypeId,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime checkIn,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime checkOut,
        Integer guestNumber
) implements Serializable {
}
