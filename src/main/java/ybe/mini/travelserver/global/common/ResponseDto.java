package ybe.mini.travelserver.global.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;

public record ResponseDto<T> (
        int status,
        T data
) {
}
