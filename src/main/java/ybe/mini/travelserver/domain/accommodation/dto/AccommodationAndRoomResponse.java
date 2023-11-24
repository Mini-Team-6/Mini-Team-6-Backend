package ybe.mini.travelserver.domain.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccommodationAndRoomResponse(
        Long Id,
        String name,
        String address,
        String image,
        List<RoomGetResponse> roomGetResponse
) {
    @Builder
    public static AccommodationAndRoomResponse fromEntity(
            Accommodation accommodation,
            List<RoomGetResponse> roomGetResponse
    ) {
        return new AccommodationAndRoomResponse(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getLocation().getAddress(),
                accommodation.getImage(),
                roomGetResponse
        );
    }
}