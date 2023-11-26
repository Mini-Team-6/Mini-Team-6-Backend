package ybe.mini.travelserver.domain.reservation.dto;

import lombok.Builder;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation.entity.ReservationStatus;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;

import java.io.Serializable;
import java.util.List;

@Builder
public record ReservationCreateResponse(

        Long id,
        ReservationStatus status

) implements Serializable {

    public static ReservationCreateResponse fromEntity(Reservation reservation) {
        return ReservationCreateResponse.builder()
                .id(reservation.getId())
                .status(reservation.getStatus())
                .build();
    }
}
