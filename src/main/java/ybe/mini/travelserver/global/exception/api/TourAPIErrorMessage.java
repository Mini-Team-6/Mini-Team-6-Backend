package ybe.mini.travelserver.global.exception.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.global.exception.ErrorMessage;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Getter
@AllArgsConstructor
public enum TourAPIErrorMessage implements ErrorMessage {
    NO_ACCOMMODATIONS_FROM_API(BAD_REQUEST, "API로부터 숙소를 가져오지 못했습니다."),
    NO_ROOMS_FROM_API(BAD_REQUEST, "API로부터 객실을 가져오지 못했습니다."),
    WRONG_CALLBACK(SERVICE_UNAVAILABLE, "잘못된 콜백입니다.");
    private final HttpStatus status;
    private final String message;
}
