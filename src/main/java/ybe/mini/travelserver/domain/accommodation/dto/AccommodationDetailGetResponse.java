package ybe.mini.travelserver.domain.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccommodationDetailGetResponse(
        Long Id,
        String name,
        String address,
        String image
) {
    @Builder
    public static AccommodationDetailGetResponse fromEntity(
            Accommodation accommodation
    ) {
        return new AccommodationDetailGetResponse(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getLocation().getAddress(),
                accommodation.getImage()
        );
    }
}