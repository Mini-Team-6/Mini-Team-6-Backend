package ybe.mini.travelserver.domain.room;

import ybe.mini.travelserver.domain.accommodation.entity.Location;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.room.entity.Room;

public class DummyObjectForControllerAndService implements DummyObjectForRoom{

    public Room dummyRoom(Accommodation accommodation) {
        return Room.builder()
                .capacity(2)
                .description("객실 설명 1")
                .image("이미지 1")
                .name("객실 이름 1")
                .price(100000)
                .roomTypeId(1L)
                .stock(50)
                .accommodation(accommodation)
                .build();
    }

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

    public Accommodation dummyAccommodation() {
        return Accommodation.builder()
                .id(1L)
                .name("세인트존스 호텔")
                .location(Location.builder()
                        .address("강원특별자치도 강릉시 창해로 307 ")
                        .phone("033-660-9000")
                        .areaCode("32")
                        .latitude(37.7940780970)
                        .longitude(128.9186301059)
                        .build())
                .image("http://tong.visitkorea.or.kr/cms/resource/54/2603354_image2_1.jpg")
                .description("푸른 해송숲과 청정 바다가 펼쳐진 강문해변에서의 자연 휴양과...")
                .build();
    }
}
