package ybe.mini.travelserver.domain.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import ybe.mini.travelserver.domain.reservation.entity.ReservationStatus;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomGetResponse;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ReservationGetResponse(
        Long id,
        List<ReservationRoomGetResponse> reservationRooms,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime createdAt,
        ReservationStatus status

) implements Serializable {
}
