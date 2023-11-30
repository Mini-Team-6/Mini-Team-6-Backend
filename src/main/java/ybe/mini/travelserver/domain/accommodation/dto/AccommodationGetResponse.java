package ybe.mini.travelserver.domain.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.entity.Location;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccommodationGetResponse(
        Long AccommodationId,
        String description,
        String image,
        Location location,
        String name
) {
    @Builder
    public static AccommodationGetResponse fromEntity(Accommodation accommodation) {
        return new AccommodationGetResponse(
                accommodation.getId(),
                accommodation.getDescription(),
                accommodation.getImage(),
                accommodation.getLocation(),
                accommodation.getName()
        );
    }
}
