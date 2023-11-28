package ybe.mini.travelserver.domain.cart.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.Builder;
import ybe.mini.travelserver.domain.accommodation.entity.AreaCode;

import java.time.LocalDate;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record CartCreateRequest(

        @Positive
        Long roomTypeId,

        @Pattern(regexp = "\\d{8}", message = "날짜 입력은 8자리 숫자이어야 합니다.")
        String checkIn,

        @Pattern(regexp = "\\d{8}", message = "날짜 입력은 8자리 숫자이어야 합니다.")
        String checkOut,

        @Positive
        Integer guestNumber,

        @Positive
        Long accommodationId,

        @NotBlank
        String keyword,

        @NotNull
        AreaCode areaCode
) {

}