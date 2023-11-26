package ybe.mini.travelserver.domain.reservation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;

import java.io.Serializable;
import java.util.List;

@Builder
public record ReservationCreateRequest(
        List<ReservationRoomCreateRequest> reservationRooms
) implements Serializable {
}
