package ybe.mini.travelserver.domain.cart.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.cart.entity.Cart;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.entity.Room;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CartGetResponse (
    Long id,
    Integer guestNumber,
    LocalDateTime checkIn,
    LocalDateTime checkOut,
    RoomGetResponse roomGetResponse,
    AccommodationGetResponse accommodationGetResponse
) {
    public static CartGetResponse fromEntity(Cart cart, Room room, Accommodation accommodation) {
        return new CartGetResponse(
                cart.getId(),
                cart.getGuestNumber(),
                cart.getCheckIn(),
                cart.getCheckOut(),
                RoomGetResponse.fromEntity(room),
                AccommodationGetResponse.fromEntity(accommodation)
        );
    }
}