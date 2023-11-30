package ybe.mini.travelserver.domain.cart.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Positive;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CartCreateResponse(
        @Positive
        Long id
) {
}
