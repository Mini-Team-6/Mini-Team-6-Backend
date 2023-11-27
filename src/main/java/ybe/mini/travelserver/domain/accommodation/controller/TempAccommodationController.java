package ybe.mini.travelserver.domain.accommodation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.accommodation.entity.AreaCode;
import ybe.mini.travelserver.domain.accommodation.entity.Location;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationAndRoomResponse;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.room.dto.RoomGetResponse;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp/accommodations")
public class TempAccommodationController {

    @GetMapping("/page/{pageNo}")
    public ResponseDto<List<AccommodationGetResponse>> searchAccommodations(
            @PathVariable int pageNo,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, value = "area-code") AreaCode areaCode
    ) {
        Location location = Location.builder()
                .address("강원특별자치도 강릉시 창해로 307 ")
                .phone("033-660-9000")
                .areaCode("32")
                .latitude(37.7940780970)
                .longitude(128.9186301059)
                .build();

        Accommodation accommodation = Accommodation.builder()
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
                .name("신라 호텔")
                .location(location1)
                .image("image1.jpg")
                .description("신라 호텔 세부 내용")
                .build();

        AccommodationGetResponse accommodationGetResponse =
                AccommodationGetResponse.fromEntity(accommodation);
        AccommodationGetResponse accommodationGetResponse1 =
                AccommodationGetResponse.fromEntity(accommodation1);


        return new ResponseDto<>(HttpStatus.OK.value(),
                List.of(accommodationGetResponse, accommodationGetResponse1));
    }

    @GetMapping("/{accommodationId}")
    public ResponseDto<AccommodationAndRoomResponse> getAccommodationAndRooms(
            @PathVariable Long accommodationId,
            @RequestParam(required = false, defaultValue = "_") String keyword
    ) {
        Accommodation accommodation = Accommodation.builder()
                .name("세인트존스 호텔")
                .location(Location.builder()
                        .address("강원특별자치도 강릉시 창해로 307 ")
                        .build())
                .image("http://tong.visitkorea.or.kr/cms/resource/54/2603354_image2_1.jpg")
                .build();

        AccommodationGetResponse accommodationGetResponse =
                AccommodationGetResponse.fromEntity(accommodation);

        Room room = Room.builder()
                .capacity(2)
                .description("객실 설명 1")
                .price(100000)
                .image("이미지 1")
                .name("객실 이름 1")
                .roomTypeId(1L)
                .stock(50)
                .build();

        Room room1 = Room.builder()
                .capacity(4)
                .description("객실 설명 2")
                .price(200000)
                .image("이미지 2")
                .name("객실 이름 2")
                .roomTypeId(2L)
                .stock(30)
                .build();

        RoomGetResponse roomGetResponse = RoomGetResponse.fromEntity(room);
        RoomGetResponse roomGetResponse1 = RoomGetResponse.fromEntity(room1);

        AccommodationAndRoomResponse accommodationAndRoomResponse =
                AccommodationAndRoomResponse.fromEntity(accommodationGetResponse,
                        List.of(roomGetResponse, roomGetResponse1));

        return new ResponseDto<>(HttpStatus.OK.value(), accommodationAndRoomResponse);
    }

}