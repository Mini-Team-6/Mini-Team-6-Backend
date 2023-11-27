package ybe.mini.travelserver.global.exception.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.global.exception.ErrorMessage;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum JwtErrorMessage implements ErrorMessage {
    ALGORITHM_MISMATCH(BAD_REQUEST, "알고리즘 불일치"),

    INVALID_CLAIM(UNAUTHORIZED, "클레임이 유효하지 않음"),

    JWT_CREATION(INTERNAL_SERVER_ERROR, "JWT 생성 실패"),

    JWT_DECODE(INTERNAL_SERVER_ERROR, "JWT 디코딩 실패"),

    JWT_VERIFICATION(INTERNAL_SERVER_ERROR, "JWT 검증 실패"),

    SIGNATURE_GENERATION(INTERNAL_SERVER_ERROR, "서명 생성 실패"),

    SIGNATURE_VERIFICATION(INTERNAL_SERVER_ERROR, "위/변조된 서명"),

    TOKEN_EXPIRED(UNAUTHORIZED, "토큰 만료");

    private final HttpStatus status;
    private final String message;
}
