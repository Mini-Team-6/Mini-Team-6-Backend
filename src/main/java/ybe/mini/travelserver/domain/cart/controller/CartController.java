package ybe.mini.travelserver.domain.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.accommodation.Accommodation;
import ybe.mini.travelserver.domain.accommodation.AccommodationType;
import ybe.mini.travelserver.domain.accommodation.Location;
import ybe.mini.travelserver.domain.cart.Cart;
import ybe.mini.travelserver.domain.cart.dto.request.CartCreateRequest;
import ybe.mini.travelserver.domain.cart.dto.response.CartGetResponse;
import ybe.mini.travelserver.domain.room.Room;
import ybe.mini.travelserver.global.common.ResponseDto;
import ybe.mini.travelserver.global.security.PrincipalDetails;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {


    // 장바구니 전체 조회
    @GetMapping
    public ResponseDto<List<CartGetResponse>> getAllCart(@AuthenticationPrincipal PrincipalDetails principalDetails)
    {
        Location location1 = Location.builder()
                .address("강원특별자치도 강릉시 창해로 307 ")
                .phone("033-660-9000")
                .areaCode("32")
                .latitude(37.7940780970)
                .longitude(128.9186301059)
                .build();

        Accommodation accommodation1 = Accommodation.builder()
                .accommodationType(AccommodationType.TOURIST_HOTEL)
                .name("세인트존스 호텔")
                .location(location1)
                .image("http://tong.visitkorea.or.kr/cms/resource/54/2603354_image2_1.jpg")
                .description("푸른 해송숲과 청정 바다가 펼쳐진 강문해변에서의 자연 휴양과 " +
                        "호텔 내에서 여유로운 휴양을 모두 즐길 수 있는 곳, 세인트존스 호텔이다...")
                .build();

        Location location2 = Location.builder()
                .address("asd ")
                .phone("010-1234-1234")
                .areaCode("31")
                .latitude(36.5484780970)
                .longitude(129.4586301059)
                .build();

        Accommodation accommodation2 = Accommodation.builder()
                .accommodationType(AccommodationType.TOURIST_HOTEL)
                .name("신라 호텔")
                .location(location2)
                .image("image1.jpg")
                .description("신라 호텔 세부 내용")
                .build();
        Room room1 = Room.builder()
                .capacity(2)
                .description("객실 설명 1")
                .image("이미지 1")
                .name("객실 이름 1")
                .price(100000)
                .roomTypeId(1L)
                .stock(50)
                .build();
        Room room2 = Room.builder()
                .capacity(2)
                .description("객실 설명 1")
                .image("이미지 1")
                .name("객실 이름 1")
                .price(100000)
                .roomTypeId(1L)
                .stock(50)
                .build();

        Cart cart1 = Cart.builder()
                .id(1L)
                .room(room1)
                .checkIn(LocalDateTime.of(2023, 1,1,15,0))
                .checkOut(LocalDateTime.of(2023, 1,5,12,0))
                .guestNumber(2)
                .build();
        Cart cart2 = Cart.builder()
                .id(2L)
                .room(room2)
                .checkIn(LocalDateTime.of(2023, 2,1,15,0))
                .checkOut(LocalDateTime.of(2023, 2,5,12,0))
                .guestNumber(3)
                .build();

        CartGetResponse cartGetResponse1 = CartGetResponse.fromEntity(cart1, accommodation1);
        CartGetResponse cartGetResponse2 = CartGetResponse.fromEntity(cart2, accommodation2);

        return new ResponseDto<>(HttpStatus.OK.value(), List.of(cartGetResponse1, cartGetResponse2));
    }

    // 장바구니 조회
    @GetMapping("/{cartId}")
    public ResponseDto<CartGetResponse> getCart(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                @PathVariable long cartId) {

        Location location1 = Location.builder()
                .address("강원특별자치도 강릉시 창해로 307 ")
                .phone("033-660-9000")
                .areaCode("32")
                .latitude(37.7940780970)
                .longitude(128.9186301059)
                .build();

        Accommodation accommodation1 = Accommodation.builder()
                .accommodationType(AccommodationType.TOURIST_HOTEL)
                .name("세인트존스 호텔")
                .location(location1)
                .image("http://tong.visitkorea.or.kr/cms/resource/54/2603354_image2_1.jpg")
                .description("푸른 해송숲과 청정 바다가 펼쳐진 강문해변에서의 자연 휴양과 " +
                        "호텔 내에서 여유로운 휴양을 모두 즐길 수 있는 곳, 세인트존스 호텔이다...")
                .build();

        Room room1 = Room.builder()
                .capacity(2)
                .description("객실 설명 1")
                .image("이미지 1")
                .name("객실 이름 1")
                .price(100000)
                .roomTypeId(1L)
                .stock(50)
                .build();
        Cart cart1 = Cart.builder()
                .id(cartId)
                .room(room1)
                .checkIn(LocalDateTime.of(2023, 2,1,15,0))
                .checkOut(LocalDateTime.of(2023, 2,5,12,0))
                .guestNumber(3)
                .build();

        CartGetResponse cartGetResponse = new CartGetResponse(
                cart1.getId(),
                cart1.getGuestNumber(),
                cart1.getCheckIn(),
                cart1.getCheckOut(),
                room1,
                accommodation1
        );
        return new ResponseDto<>(HttpStatus.OK.value(), cartGetResponse);
    }

    // 장바구니 추가
    @PostMapping
    public ResponseDto<Long> postCart(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                    @RequestBody CartCreateRequest cartCreateRequest) {
        Long cartId = 1L;
        return new ResponseDto<>(HttpStatus.CREATED.value(), cartId);
    }

    // 장바구니 삭제
    @DeleteMapping("/{cartId}")
    public ResponseDto<?> deleteCart(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @PathVariable long cartId) {
        return new ResponseDto<>(HttpStatus.OK.value(), cartId);
    }
}
