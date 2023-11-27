package ybe.mini.travelserver.domain.cart.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import ybe.mini.travelserver.domain.cart.entity.Cart;

import java.time.LocalDateTime;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record CartCreateRequest(
    @NotBlank
    Long roomId,
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime checkIn,
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime checkOut,
    @NotBlank
    Integer guestNumber

) {
    public static CartCreateRequest fromEntity(Cart cart) {
        return new CartCreateRequest(
                cart.getId(),
                cart.getCheckIn(),
                cart.getCheckOut(),
                cart.getGuestNumber()
        );
    }
}