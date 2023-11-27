package ybe.mini.travelserver.domain.reservation_room.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record ReservationRoomCreateRequest(

        @NotNull
        Long accommodationId,
        @NotBlank
        String accommodationName,
        @NotBlank
        String areaCode,
        @NotNull
        Long roomTypeId,

        @NotNull
        @FutureOrPresent
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime checkIn,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime checkOut,
        @NotNull
        @Min(value = 1, message = "숙박 인원은 최소 1명 이어야 합니다.")
        Integer guestNumber
) implements Serializable {
}
