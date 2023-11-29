package ybe.mini.travelserver.domain.cart.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.domain.cart.dto.request.CartCreateRequest;
import ybe.mini.travelserver.domain.cart.dto.response.CartCreateResponse;
import ybe.mini.travelserver.domain.cart.dto.response.CartDeleteResponse;
import ybe.mini.travelserver.domain.cart.dto.response.CartGetResponse;
import ybe.mini.travelserver.domain.cart.dummy.DummyCart;
import ybe.mini.travelserver.domain.cart.service.CartService;
import ybe.mini.travelserver.domain.member.dummy.DummyPrincipal;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static ybe.mini.travelserver.domain.accommodation.entity.AreaCode.SEOUL;

@ExtendWith(MockitoExtension.class)
class CartControllerTest implements DummyPrincipal, DummyCart {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @DisplayName("장바구니를 조회")
    @Test
    void getCart() {
        // given
        CartGetResponse cartGetResponse =
                CartGetResponse.fromEntity(dummyCart(), dummyRoom(dummyAccommodation()), dummyAccommodation());
        CartGetResponse cartGetResponse1 =
                CartGetResponse.fromEntity(dummyCart(), dummyRoom(dummyAccommodation()), dummyAccommodation());
        List<CartGetResponse> cartGetResponseList =
                List.of(cartGetResponse, cartGetResponse1);
        given(cartService.getMyCarts(anyLong())).willReturn(cartGetResponseList);

        // when
        ResponseDto<List<CartGetResponse>> responseDto =
                cartController.getAllCart(dummyPrincipalDetails());

        // then
        assertEquals(HttpStatus.OK.value(), responseDto.status());
        assertEquals(responseDto.data(), cartGetResponseList);
        then(cartService).should().getMyCarts(anyLong());
    }

    @DisplayName("장바구니를 생성")
    @Test
    void createCart() {
        // given
        CartCreateRequest cartCreateRequest =
                CartCreateRequest.builder()
                        .accommodationId(142785L)
                        .guestNumber(1)
                        .keyword("가락관광호텔")
                        .areaCode(SEOUL)
                        .checkIn("20241120")
                        .checkOut("20241123")
                        .roomTypeId(11430L)
                        .build();
        CartCreateResponse cartCreateResponse = new CartCreateResponse(1L);

        given(cartService.createCart(anyLong(), any()))
                .willReturn(cartCreateResponse);

        // when
        ResponseDto<CartCreateResponse> responseDto =
                cartController.createCart(dummyPrincipalDetails(), cartCreateRequest);

        // then
        assertEquals(HttpStatus.CREATED.value(), responseDto.status());
        assertEquals(responseDto.data(), cartCreateResponse);
        then(cartService).should().createCart(anyLong(), any());

    }

    @DisplayName("장바구니를 삭제")
    @Test
    void deleteCart() {
        // given
        Long cartId = 1L;
        CartDeleteResponse cartDeleteResponse = new CartDeleteResponse(1L);
        given(cartService.deleteCart(anyLong())).willReturn(cartDeleteResponse);

        // when
        ResponseDto<CartDeleteResponse> responseDto =
                cartController.deleteCart(dummyPrincipalDetails(), cartId);

        // then
        assertEquals(HttpStatus.OK.value(), responseDto.status());
        assertEquals(responseDto.data(), cartDeleteResponse);
        then(cartService).should().deleteCart(anyLong());
    }

}