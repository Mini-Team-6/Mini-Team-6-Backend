package ybe.mini.travelserver.domain.reservation_room.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import ybe.mini.travelserver.domain.accommodation.entity.AreaCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
public record ReservationRoomCreateRequest(

        @NotNull(message = "accommodationId 에 null 이 입력되었습니다.")
        Long accommodationId,
        @NotBlank(message = "accommodationName 에 빈 값이 입력되었습니다.")
        String accommodationName,
        @NotNull(message = "areaCode 양식이 잘못 입력되었습니다.")
        AreaCode areaCode,
        @NotNull(message = "roomTypeId 양식이 잘못 입력되었습니다.")
        Long roomTypeId,
        @Pattern(regexp = "\\d{8}", message = "날짜 입력은 8자리 숫자이어야 합니다.")
        String checkIn,
        @Pattern(regexp = "\\d{8}", message = "날짜 입력은 8자리 숫자이어야 합니다.")
        String checkOut,
        @NotNull
        @Min(value = 1, message = "숙박 인원은 최소 1명 이어야 합니다.")
        Integer guestNumber
) implements Serializable {
}
