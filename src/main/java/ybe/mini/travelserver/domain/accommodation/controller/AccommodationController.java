package ybe.mini.travelserver.domain.accommodation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ybe.mini.travelserver.domain.accommodation.AccommodationType;
import ybe.mini.travelserver.domain.accommodation.Location;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accomodations")
public class AccommodationController {

    @GetMapping("/keyword") //TODO : url 수정 예정
    public ResponseDto<List<AccommodationGetResponse>> getAccommodationByKeyword(
            @RequestParam String keyword
    ) {
        Location location = Location.builder()
                .address("강원특별자치도 강릉시 창해로 307 ")
                .phone("033-660-9000")
                .areaCode("32")
                .latitude(37.7940780970)
                .longitude(128.9186301059)
                .build();

        Accommodation accommodation = Accommodation.builder()
                .accommodationType(AccommodationType.TOURIST_HOTEL)
                .name("세인트존스 호텔")
                .location(location)
                .image("http://tong.visitkorea.or.kr/cms/resource/54/2603354_image2_1.jpg")
                .description("푸른 해송숲과 청정 바다가 펼쳐진 강문해변에서의 자연 휴양과 " +
                        "호텔 내에서 여유로운 휴양을 모두 즐길 수 있는 곳, 세인트존스 호텔이다...")
                .build();

        Location location1 = Location.builder()
                .address("asd ")
                .phone("010-1234-1234")
                .areaCode("31")
                .latitude(36.5484780970)
                .longitude(129.4586301059)
                .build();

        Accommodation accommodation1 = Accommodation.builder()
                .accommodationType(AccommodationType.TOURIST_HOTEL)
                .name("신라 호텔")
                .location(location1)
                .image("image1.jpg")
                .description("신라 호텔 세부 내용")
                .build();

        AccommodationGetResponse accommodationGetResponse =
                AccommodationGetResponse.fromEntity(accommodation);
        AccommodationGetResponse accommodationGetResponse1 =
                AccommodationGetResponse.fromEntity(accommodation1);

        List<AccommodationGetResponse> responseList =
                new ArrayList<>();
        responseList.add(accommodationGetResponse);
        responseList.add(accommodationGetResponse1);


        return new ResponseDto<>(HttpStatus.OK.value(), responseList);
    }

    @GetMapping("")
    public ResponseDto<List<AccommodationGetResponse>> getAccommodationByCityAndDistrict(
            @RequestParam String city, @RequestParam String district
    ) {
        Location location = Location.builder()
                .address("강원특별자치도 강릉시 창해로 307 ")
                .phone("033-660-9000")
                .areaCode("32")
                .latitude(37.7940780970)
                .longitude(128.9186301059)
                .build();

        Accommodation accommodation = Accommodation.builder()
                .accommodationType(AccommodationType.TOURIST_HOTEL)
                .name("세인트존스 호텔")
                .location(location)
                .image("http://tong.visitkorea.or.kr/cms/resource/54/2603354_image2_1.jpg")
                .description("푸른 해송숲과 청정 바다가 펼쳐진 강문해변에서의 자연 휴양과 " +
                        "호텔 내에서 여유로운 휴양을 모두 즐길 수 있는 곳, 세인트존스 호텔이다...")
                .build();

        Location location1 = Location.builder()
                .address("asd ")
                .phone("010-1234-1234")
                .areaCode("31")
                .latitude(36.5484780970)
                .longitude(129.4586301059)
                .build();

        Accommodation accommodation1 = Accommodation.builder()
                .accommodationType(AccommodationType.TOURIST_HOTEL)
                .name("신라 호텔")
                .location(location1)
                .image("image1.jpg")
                .description("신라 호텔 세부 내용")
                .build();

        AccommodationGetResponse accommodationGetResponse =
                AccommodationGetResponse.fromEntity(accommodation);
        AccommodationGetResponse accommodationGetResponse1 =
                AccommodationGetResponse.fromEntity(accommodation1);

        List<AccommodationGetResponse> responseList =
                new ArrayList<>();
        responseList.add(accommodationGetResponse);
        responseList.add(accommodationGetResponse1);


        return new ResponseDto<>(HttpStatus.OK.value(), responseList);
    }


    @GetMapping("/search")
    public ResponseDto<List<AccommodationGetResponse>> searchAccommodation() {
        Location location = Location.builder()
                .address("강원특별자치도 강릉시 창해로 307 ")
                .phone("033-660-9000")
                .areaCode("32")
                .latitude(37.7940780970)
                .longitude(128.9186301059)
                .build();

        Accommodation accommodation = Accommodation.builder()
                .accommodationType(AccommodationType.TOURIST_HOTEL)
                .name("세인트존스 호텔")
                .location(location)
                .image("http://tong.visitkorea.or.kr/cms/resource/54/2603354_image2_1.jpg")
                .description("푸른 해송숲과 청정 바다가 펼쳐진 강문해변에서의 자연 휴양과 " +
                        "호텔 내에서 여유로운 휴양을 모두 즐길 수 있는 곳, 세인트존스 호텔이다...")
                .build();

        Location location1 = Location.builder()
                .address("asd ")
                .phone("010-1234-1234")
                .areaCode("31")
                .latitude(36.5484780970)
                .longitude(129.4586301059)
                .build();

        Accommodation accommodation1 = Accommodation.builder()
                .accommodationType(AccommodationType.TOURIST_HOTEL)
                .name("신라 호텔")
                .location(location1)
                .image("image1.jpg")
                .description("신라 호텔 세부 내용")
                .build();

        AccommodationGetResponse accommodationGetResponse =
                AccommodationGetResponse.fromEntity(accommodation);
        AccommodationGetResponse accommodationGetResponse1 =
                AccommodationGetResponse.fromEntity(accommodation1);

        List<AccommodationGetResponse> responseList =
                new ArrayList<>();
        responseList.add(accommodationGetResponse);
        responseList.add(accommodationGetResponse1);


        return new ResponseDto<>(HttpStatus.OK.value(), responseList);
    }

}
