package ybe.mini.travelserver.domain.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation.entity.ReservationStatus;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomGetResponse;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Builder
public record ReservationGetResponse(
        Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime createdAt,
        ReservationStatus status,
        List<ReservationRoomGetResponse> reservationRooms

) implements Serializable {

        public static ReservationGetResponse fromEntity(Reservation reservation) {
                return ReservationGetResponse.builder()
                        .id(reservation.getId())
                        .reservationRooms(
                                Optional.ofNullable(reservation.getReservationRooms())
                                        .orElseGet(Collections::emptyList).stream()
                                        .map(ReservationRoomGetResponse::fromEntity).toList()
                        )
                        .createdAt(reservation.getCreatedAt())
                        .status(reservation.getStatus())
                        .build();
        }
}
