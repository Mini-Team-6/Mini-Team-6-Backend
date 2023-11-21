package ybe.mini.travelserver.domain.reservation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;

import java.io.Serializable;
import java.util.List;

public record ReservationCreateRequest(
        List<ReservationRoomCreateRequest> reservationRooms
) implements Serializable {
}
