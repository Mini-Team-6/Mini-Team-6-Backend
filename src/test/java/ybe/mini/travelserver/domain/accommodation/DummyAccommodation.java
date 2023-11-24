package ybe.mini.travelserver.domain.accommodation;

import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;

public class DummyAccommodation {
    protected Accommodation dummyAccommodation() {
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

    protected AccommodationGetResponse dummyAccommodationGetResponse() {
        return AccommodationGetResponse.fromEntity(dummyAccommodation());
    }
}
