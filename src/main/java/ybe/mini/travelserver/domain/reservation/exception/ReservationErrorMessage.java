package ybe.mini.travelserver.domain.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.global.exception.ErrorMessage;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ReservationErrorMessage implements ErrorMessage {

    RESERVATION_NOT_FOUND(BAD_REQUEST, "해당 ID의 예약 정보가 없습니다.")
    ;
    private final HttpStatus status;
    private final String message;
}
