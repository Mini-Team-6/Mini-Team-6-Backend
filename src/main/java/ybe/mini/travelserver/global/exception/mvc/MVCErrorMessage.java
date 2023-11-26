package ybe.mini.travelserver.global.exception.mvc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.global.exception.ErrorMessage;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum MVCErrorMessage implements ErrorMessage {
    NO_HANDLER_FOUND(NOT_FOUND, "요청하신 페이지를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
