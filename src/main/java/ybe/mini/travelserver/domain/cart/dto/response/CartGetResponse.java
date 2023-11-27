package ybe.mini.travelserver.domain.cart.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.cart.entity.Cart;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.entity.Room;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CartGetResponse (
    @Positive
    Long id,

    @Positive
    Integer guestNumber,

    @FutureOrPresent
    LocalDate checkIn,

    @FutureOrPresent
    LocalDate checkOut,

    @Valid
    RoomGetResponse roomGetResponse,

    @Valid
    AccommodationGetResponse accommodationGetResponse
) {
    public static CartGetResponse fromEntity(
            Cart cart,
            Room room,
            Accommodation accommodation
    ) {
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