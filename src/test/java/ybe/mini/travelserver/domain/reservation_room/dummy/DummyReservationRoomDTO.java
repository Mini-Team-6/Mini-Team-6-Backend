package ybe.mini.travelserver.domain.reservation_room.dummy;

import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomGetResponse;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus;
import ybe.mini.travelserver.domain.room.DummyObjectForRoom;

import java.time.LocalDate;

public interface DummyReservationRoomDTO extends DummyObjectForRoom {

    default ReservationRoomCreateRequest dummyReservationRoomCreateReq1() {
        return ReservationRoomCreateRequest.builder()
                .accommodationId(14253L)
                .accommodationName("청룡관광호텔")
                .roomTypeId(5352L)
                .checkIn("20241101")
                .checkOut("20241102")
                .guestNumber(2)
                .build();
    }

    default ReservationRoomCreateRequest dummyReservationRoomCreateReq2() {
        return ReservationRoomCreateRequest.builder()
                .accommodationId(14253L)
                .accommodationName("청룡관광호텔")
                .roomTypeId(5352L)
                .checkIn("20240101")
                .checkOut("20241102")
                .guestNumber(2)
                .build();
    }

    default ReservationRoomGetResponse dummyReservationRoomGetRes() {
        return ReservationRoomGetResponse.builder()
                .id(1L)
                .checkIn(LocalDate.of(2024, 1, 1))
                .checkOut(LocalDate.of(2024, 1, 2))
                .guestNumber(2)
                .status(ReservationRoomStatus.RESERVED)
                .room(dummyRoomGetResponse())
                .build();
    }
}
