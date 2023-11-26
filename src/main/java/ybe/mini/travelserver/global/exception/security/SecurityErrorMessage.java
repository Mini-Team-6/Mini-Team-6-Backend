package ybe.mini.travelserver.global.exception.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.global.exception.ErrorMessage;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum SecurityErrorMessage implements ErrorMessage {
    AUTHENTICATION_FAILED(UNAUTHORIZED, "인증에 실패하였습니다."),
    ACCESS_DENIED(FORBIDDEN, "접근이 거부되었습니다."),
    UN_AUTHORIZED(UNAUTHORIZED, "인증되지 않은 요청입니다."),
    INSUFFICIENT_PERMISSION(FORBIDDEN, "권한이 부족합니다."),

    BAD_CREDENTIALS(UNAUTHORIZED, "자격 증명에 실패하였습니다."),
    USERNAME_NOT_FOUND(NOT_FOUND, "계정을 찾을 수 없습니다."),
    ACCOUNT_EXPIRED(UNAUTHORIZED, "계정이 만료되었습니다."),
    CREDENTIALS_EXPIRED(UNAUTHORIZED, "비밀번호가 만료되었습니다."),
    DISABLED(UNAUTHORIZED, "계정이 비활성화되었습니다."),
    LOCKED(UNAUTHORIZED, "계정이 잠겨 있습니다.");

    private final HttpStatus status;
    private final String message;
}