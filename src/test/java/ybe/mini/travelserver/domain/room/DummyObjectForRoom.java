package ybe.mini.travelserver.domain.room;

import ybe.mini.travelserver.domain.accommodation.entity.Location;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.entity.Room;

public interface DummyObjectForRoom {

    default Room dummyRoom(Accommodation accommodation) {
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

    default Room dummyRoom2(Accommodation accommodation) {
        return Room.builder()
                .capacity(4)
                .description("객실 설명 2")
                .image("이미지 2")
                .name("객실 이름 2")
                .price(850000)
                .roomTypeId(1L)
                .stock(30)
                .accommodation(accommodation)
                .build();
    }

    default Accommodation dummyAccommodation() {
        return Accommodation.builder()
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

    default RoomGetResponse dummyRoomGetResponse() {
        return RoomGetResponse.fromEntity(dummyRoom(dummyAccommodation()));
    }


}
