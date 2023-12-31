package ybe.mini.travelserver.global.exception.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.global.exception.ErrorMessage;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum TourAPIErrorMessage implements ErrorMessage {
    // Tour API 숙박, 객실 비즈니스 오류
    NO_ACCOMMODATIONS_FROM_API(NO_CONTENT, "숙소 결과가 반환된 것이 없습니다"),
    NO_ROOMS_FROM_API(NO_CONTENT, "객실 결과가 반환된 것이 없습니다"),

    // Tour API Keyword 검색 부분 오류
    INVALID_REQUEST_PARAMETER_ERROR(BAD_REQUEST, "잘못된 요청 파라메터입니다."),
    NO_MANDATORY_REQUEST_PARAMETERS_ERROR(BAD_REQUEST, "필수 요청 파라메터가 없습니다."),
    TEMPORARILY_DISABLE_THE_SERVICEKEY_ERROR(SERVICE_UNAVAILABLE, "일시적으로 사용할 수 없는 서비스 키입니다."),
    UNSIGNED_CALL_ERROR(INTERNAL_SERVER_ERROR, "서명되지 않은 호출입니다."),
    NODATA_ERROR(INTERNAL_SERVER_ERROR, "데이터가 없습니다."),
    SERVICETIMEOUT_ERROR(GATEWAY_TIMEOUT, "서비스 연결 실패입니다."),
    DB_ERROR(INTERNAL_SERVER_ERROR, "데이터베이스 에러입니다."),

    // Tour API 공통 오류
    APPLICATION_ERROR(SERVICE_UNAVAILABLE, "Tour API 서버 어플리케이션 에러입니다"),
    HTTP_ERROR(SERVICE_UNAVAILABLE, "Tour API 서버 HTTP 에러입니다"),
    NO_OPENAPI_SERVICE_ERROR(SERVICE_UNAVAILABLE, "해당 Tour API 서비스가 없거나 폐기되었습니다"),
    SERVICE_ACCESS_DENIED_ERROR(SERVICE_UNAVAILABLE, "Tour API 서비스 접근이 거부되었습니다"),
    LIMITED_NUMBER_OF_SERVICE_REQUESTS_EXCEEDS_ERROR(SERVICE_UNAVAILABLE, "Tour API 서비스 요청 제한 횟수를 초과하였습니다"),
    SERVICE_KEY_IS_NOT_REGISTERED_ERROR(SERVICE_UNAVAILABLE, "등록되지 않은 Tour API 서비스 키입니다"),
    DEADLINE_HAS_EXPIRED_ERROR(SERVICE_UNAVAILABLE, "Tour API 서비스 활용 기간이 만료되었습니다"),
    UNREGISTERED_IP_ERROR(SERVICE_UNAVAILABLE, "등록되지 않은 Tour API 서비스 IP입니다"),
    UNKNOWN_ERROR(SERVICE_UNAVAILABLE, "Tour API 서버 알 수 없는 에러입니다");

    private final HttpStatus status;
    private final String message;
}
