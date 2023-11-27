package ybe.mini.travelserver.domain.cart.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import ybe.mini.travelserver.domain.accommodation.entity.AreaCode;

import java.time.LocalDate;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record CartCreateRequest(

        @Positive
        Long roomTypeId,

        @FutureOrPresent
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate checkIn,

        @FutureOrPresent
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate checkOut,

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