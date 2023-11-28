package ybe.mini.travelserver.global.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.global.exception.ErrorMessage;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum DTOErrorMessage implements ErrorMessage {
    METHOD_ARGUMENT_NOT_VALID(BAD_REQUEST, "DTO 값이 유효하지 않습니다"),
    DATETIME_PARSE(BAD_REQUEST, "날짜 형식이 유효하지 않습니다"),
    DATETIME_NOT_CURRENT_OR_FUTURE(BAD_REQUEST, "날짜가 현재 날짜보다 같거나 이후여야합니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
