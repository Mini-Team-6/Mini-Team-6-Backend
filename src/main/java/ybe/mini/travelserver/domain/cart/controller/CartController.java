package ybe.mini.travelserver.domain.cart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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

        CartGetResponse cartGetResponse1 = CartGetResponse.fromEntity(cart1);
        CartGetResponse cartGetResponse2 = CartGetResponse.fromEntity(cart2);

        return new ResponseDto<>(HttpStatus.OK.value(), List.of(cartGetResponse1, cartGetResponse2));
    }

    // 장바구니 조회
    @GetMapping("/{cartId}")
    public ResponseDto<CartGetResponse> getCart(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                @PathVariable long cartId) {
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
        CartGetResponse cartGetResponse = CartGetResponse.fromEntity(cart1);
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
