package ybe.mini.travelserver.domain.room.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import ybe.mini.travelserver.domain.room.entity.Room;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RoomGetResponse(
        Long Id,
        Integer capacity,
        String description,
        Integer price,
        String image,
        String name,
        Long roomTypeId,
        Integer stock
) {
    @Builder
    public static RoomGetResponse fromEntity(Room room) {
        return new RoomGetResponse(
                room.getId(),
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
