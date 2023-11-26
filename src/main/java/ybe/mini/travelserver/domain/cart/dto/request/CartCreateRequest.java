package ybe.mini.travelserver.domain.cart.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record CartCreateRequest(
    @NotBlank
    Long roomTypeId,
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate checkIn,
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate checkOut,
    @NotBlank
    Integer guestNumber,
    @NotBlank
    Long accommodationId

) {
//    public static CartCreateRequest fromEntity(Cart cart) {
//        return new CartCreateRequest(
//                cart.getId(),
//                cart.getCheckIn(),
//                cart.getCheckOut(),
//                cart.getGuestNumber()
//        );
//    }
}