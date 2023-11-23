package ybe.mini.travelserver.global.api.dto.common;

public record Body<T>(
        Items<T> items,
        int numOfRows,
        int pageNo,
        int totalCount
) {
}
