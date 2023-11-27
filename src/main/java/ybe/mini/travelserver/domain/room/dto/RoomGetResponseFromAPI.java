package ybe.mini.travelserver.domain.room.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import ybe.mini.travelserver.domain.room.entity.Room;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RoomGetResponseFromAPI(
        Integer capacity,
        String description,
        Integer price,
        String image,
        String name,
        Long roomTypeId,
        Integer stock
) {
    @Builder
    public static RoomGetResponseFromAPI fromEntity(Room room) {
        return new RoomGetResponseFromAPI(
                room.getCapacity(),
                room.getDescription(),
                room.getPrice(),
                room.getImage(),
                room.getName(),
                room.getRoomTypeId(),
                room.getStock()
        );
    }
}
