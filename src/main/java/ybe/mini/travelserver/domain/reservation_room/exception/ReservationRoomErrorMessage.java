package ybe.mini.travelserver.domain.reservation_room.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.global.exception.ErrorMessage;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ReservationRoomErrorMessage implements ErrorMessage {

    RESERVATION_ROOM_NOT_FOUND(BAD_REQUEST, "해당 ID의 예약 객실 정보가 없습니다.")
    ;
    private final HttpStatus status;
    private final String message;
}
