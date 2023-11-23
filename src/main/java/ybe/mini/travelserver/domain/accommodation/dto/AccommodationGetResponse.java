package ybe.mini.travelserver.domain.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import ybe.mini.travelserver.domain.accommodation.entity.AccommodationType;
import ybe.mini.travelserver.domain.accommodation.Location;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record AccommodationGetResponse(
        AccommodationType accommodationType,
        String description,
        String image,
        Location location,
        String name
) {
    @Builder
    public static AccommodationGetResponse fromEntity(Accommodation accommodation) {
        return new AccommodationGetResponse(
                accommodation.getAccommodationType(),
                accommodation.getDescription(),
                accommodation.getImage(),
                accommodation.getLocation(),
                accommodation.getName()
        );
    }
}
