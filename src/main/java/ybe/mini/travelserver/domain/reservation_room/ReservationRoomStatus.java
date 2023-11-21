package ybe.mini.travelserver.domain.reservation_room;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationRoomStatus {
    RESERVED("예약됨"),
    CANCELED("취소됨"),
    CHECKED_IN("체크인됨"),
    CHECKED_OUT("체크아웃됨");

    private final String name;

}
