package ybe.mini.travelserver.domain.room;

import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.room.entity.Room;

public class DummyObjectForControllerAndService implements DummyObjectForRoom {

    public Room dummyRoom1(Accommodation accommodation) {
        return Room.builder()
                .capacity(4)
                .description("객실 설명 2")
                .image("이미지 2")
                .name("객실 이름 2")
                .price(85000)
                .roomTypeId(2L)
                .stock(20)
                .accommodation(accommodation)
                .build();
    }
}
