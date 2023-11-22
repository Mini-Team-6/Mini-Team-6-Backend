package ybe.mini.travelserver.domain.cart.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import ybe.mini.travelserver.domain.cart.Cart;
import ybe.mini.travelserver.domain.room.Room;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record CartGetResponse (
    Long id,
    int guestNumber,
    LocalDateTime checkIn,
    LocalDateTime checkOut,
    Room room
) {
    @Builder
    public static CartGetResponse fromEntity(Cart cart) {
        return new CartGetResponse(
                cart.getId(),
                cart.getGuestNumber(),
                cart.getCheckIn(),
                cart.getCheckOut(),
                cart.getRoom()
        );
    }
}