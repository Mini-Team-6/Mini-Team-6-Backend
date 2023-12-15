package ybe.mini.travelserver.domain.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import ybe.mini.travelserver.domain.reservation.entity.PaymentType;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomGetResponse;

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
        PaymentType paymentType,
        List<ReservationRoomGetResponse> reservationRooms,
        Integer totalPage

) implements Serializable {

    public static ReservationGetResponse fromEntity(Reservation reservation, Integer totalPage) {
        return ReservationGetResponse.builder()
                .id(reservation.getId())
                .reservationRooms(
                        Optional.ofNullable(reservation.getReservationRooms())
                                .orElseGet(Collections::emptyList).stream()
                                .map(ReservationRoomGetResponse::fromEntity).toList()
                )
                .createdAt(reservation.getCreatedAt())
                .paymentType(reservation.getPaymentType())
                .totalPage(totalPage)
                .build();
    }
}
