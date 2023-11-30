package ybe.mini.travelserver.domain.reservation.dummy;

import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateResponse;
import ybe.mini.travelserver.domain.reservation.dto.ReservationGetResponse;
import ybe.mini.travelserver.domain.reservation_room.dummy.DummyReservationRoomDTO;

import java.time.LocalDateTime;
import java.util.List;

import static ybe.mini.travelserver.domain.reservation.entity.PaymentType.KAKAO_PAY;

public interface DummyReservationDTO extends DummyReservationRoomDTO {

    default ReservationCreateResponse dummyReservationCreateRes() {
        return ReservationCreateResponse.builder()
                .id(1L)
                .paymentType(KAKAO_PAY)
                .build();
    }

    default ReservationCreateRequest dummyReservationCreateReq() {
        return ReservationCreateRequest.builder()
                .reservationRooms(List.of(dummyReservationRoomCreateReq1(), dummyReservationRoomCreateReq2()))
                .build();
    }

    default ReservationGetResponse dummyReservationGetRes() {
        return ReservationGetResponse.builder()
                .id(1L)
                .createdAt(LocalDateTime.of(2022, 1, 1, 0, 0))
                .paymentType(KAKAO_PAY)
                .reservationRooms(List.of(dummyReservationRoomGetRes()))
                .build();
    }

}
