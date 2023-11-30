package ybe.mini.travelserver.domain.accommodation;

import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.entity.Location;

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
                .image("https://tong.visitkorea.or.kr/cms/resource/54/2603354_image2_1.jpg")
                .description("푸른 해송숲과 청정 바다가 펼쳐진 강문해변에서의 자연 휴양과...")
                .build();
    }

    protected Accommodation dummyAccommodation1() {
        return Accommodation.builder()
                .name("숙소 이름")
                .location(Location.builder()
                        .address("숙소 주소")
                        .phone("숙소 전화번호")
                        .areaCode("지역 코드")
                        .latitude(36.57454563)
                        .longitude(127.645468423)
                        .build())
                .image("이미지 URL")
                .description("숙소 상세 설명")
                .build();
    }

}
