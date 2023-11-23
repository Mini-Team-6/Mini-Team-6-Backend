package ybe.mini.travelserver.domain.room.dto;

import java.io.Serializable;

public record RoomCreateRequest(
        Integer capacity,
        String description,
        String image,
        String name,
        Integer price,
        Long roomTypeId,
        Integer stock

) implements Serializable {

}
