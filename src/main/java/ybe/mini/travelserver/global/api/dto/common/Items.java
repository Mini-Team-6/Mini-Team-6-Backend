package ybe.mini.travelserver.global.api.dto.common;

import java.util.List;

public record Items<T>(
        List<T> item
) {
}
