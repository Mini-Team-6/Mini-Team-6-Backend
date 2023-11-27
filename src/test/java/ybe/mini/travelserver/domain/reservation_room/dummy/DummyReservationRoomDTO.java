package ybe.mini.travelserver.domain.reservation_room.dummy;

import ybe.mini.travelserver.domain.reservation.dto.ReservationGetResponse;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomGetResponse;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus;
import ybe.mini.travelserver.domain.room.DummyObjectForRoom;

import java.time.LocalDateTime;

public interface DummyReservationRoomDTO extends DummyObjectForRoom {

    default ReservationRoomCreateRequest dummyReservationRoomCreateReq1() {
        return ReservationRoomCreateRequest.builder()
                .accommodationId(14253L)
                .accommodationName("청룡관광호텔")
                .roomTypeId(5352L)
                .checkIn(LocalDateTime.of(2022,1,1,15,0))
                .checkOut(LocalDateTime.of(2022,1,2,11,0))
                .guestNumber(2)
                .build();
    }

    default ReservationRoomCreateRequest dummyReservationRoomCreateReq2() {
        return ReservationRoomCreateRequest.builder()
                .accommodationId(14253L)
                .accommodationName("청룡관광호텔")
                .roomTypeId(5352L)
                .checkIn(LocalDateTime.of(2022,1,3,15,0))
                .checkOut(LocalDateTime.of(2022,1,4,11,0))
                .guestNumber(2)
                .build();
    }

    default ReservationRoomGetResponse dummyReservationRoomGetRes() {
        return ReservationRoomGetResponse.builder()
                .id(1L)
                .checkIn(LocalDateTime.of(2022,1,3,15,0))
                .checkOut(LocalDateTime.of(2022,1,4,11,0))
                .guestNumber(2)
                .status(ReservationRoomStatus.RESERVED)
                .room(dummyRoomGetResponse())
                .build();
    }
}
