package ybe.mini.travelserver.domain.cart.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import ybe.mini.travelserver.domain.cart.Cart;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record CartCreateRequest(
    @NotBlank
    Long roomId,
    @NotBlank
    LocalDateTime checkIn,
    @NotBlank
    LocalDateTime checkOut,
    @NotBlank
    int guestNumber

) {
    @Builder
    public static CartCreateRequest fromEntity(Cart cart) {
        return new CartCreateRequest(
                cart.getId(),
                cart.getCheckIn(),
                cart.getCheckOut(),
                cart.getGuestNumber()
        );
    }
}