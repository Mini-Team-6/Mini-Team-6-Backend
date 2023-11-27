package ybe.mini.travelserver.domain.reservation.dto;

import lombok.Builder;
import ybe.mini.travelserver.domain.reservation.entity.PaymentType;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;

import java.io.Serializable;

@Builder
public record ReservationCreateResponse(

        Long id,
        PaymentType paymentType

) implements Serializable {

    public static ReservationCreateResponse fromEntity(Reservation reservation) {
        return ReservationCreateResponse.builder()
                .id(reservation.getId())
                .paymentType(reservation.getPaymentType())
                .build();
    }
}
