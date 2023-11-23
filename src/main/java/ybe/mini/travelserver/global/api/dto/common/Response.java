package ybe.mini.travelserver.global.api.dto.common;

public record Response<T>(
        Header header,
        Body<T> body
) {
}
