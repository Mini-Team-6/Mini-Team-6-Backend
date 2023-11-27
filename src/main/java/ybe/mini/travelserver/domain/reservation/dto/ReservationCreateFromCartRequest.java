package ybe.mini.travelserver.domain.reservation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import ybe.mini.travelserver.domain.reservation.entity.PaymentType;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;

import java.io.Serializable;
import java.util.List;

@Builder
public record ReservationCreateFromCartRequest(

        @NotNull
        PaymentType paymentType,
        @NotEmpty
        List<Long> cardIds,
        @Valid
        List<ReservationRoomCreateRequest> reservationRooms
) implements Serializable {
}
