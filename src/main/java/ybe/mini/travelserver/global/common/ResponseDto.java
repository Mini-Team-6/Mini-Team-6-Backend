package ybe.mini.travelserver.global.common;

public record ResponseDto<T>(
        int status,
        T data
) {
}
