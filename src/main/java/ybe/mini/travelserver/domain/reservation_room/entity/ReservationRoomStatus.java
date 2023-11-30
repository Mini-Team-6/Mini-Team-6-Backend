package ybe.mini.travelserver.domain.reservation_room.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationRoomStatus {
    RESERVED("예약됨"),
    PAYED("결제됨");
    private final String name;

}
