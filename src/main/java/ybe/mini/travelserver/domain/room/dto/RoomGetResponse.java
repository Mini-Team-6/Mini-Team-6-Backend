package ybe.mini.travelserver.domain.room.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import ybe.mini.travelserver.domain.room.entity.Room;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record RoomGetResponse(
        Integer capacity,
        String description,
        String image,
        String name,
        Integer price,
        Long roomTypeId,
        Integer stock
) {
    @Builder
    public static RoomGetResponse fromEntity(Room room) {
        return new RoomGetResponse(
                room.getCapacity(),
                room.getDescription(),
                room.getImage(),
                room.getName(),
                room.getPrice(),
                room.getRoomTypeId(),
                room.getStock()
        );
    }
}
