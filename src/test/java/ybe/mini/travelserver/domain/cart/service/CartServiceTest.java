package ybe.mini.travelserver.domain.cart.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
import ybe.mini.travelserver.domain.cart.dto.request.CartCreateRequest;
import ybe.mini.travelserver.domain.cart.dto.response.CartCreateResponse;
import ybe.mini.travelserver.domain.cart.dto.response.CartGetResponse;
import ybe.mini.travelserver.domain.cart.dummy.DummyCart;
import ybe.mini.travelserver.domain.cart.entity.Cart;
import ybe.mini.travelserver.domain.cart.repository.CartRepository;
import ybe.mini.travelserver.domain.member.dummy.DummyPrincipal;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static ybe.mini.travelserver.domain.accommodation.entity.AreaCode.SEOUL;

@ExtendWith(MockitoExtension.class)
class CartServiceTest implements DummyPrincipal, DummyCart {
    @InjectMocks
    CartService cartService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    RoomRepository roomRepository;

    @Mock
    AccommodationRepository accommodationRepository;

    @Mock
    TourAPIService tourAPIService;

    @Test
    @DisplayName("장바구니 생성")
    void createCart_success(){
        // given
        CartCreateRequest cartCreateRequest =
                CartCreateRequest.builder()
                        .accommodationId(dummyAccommodation().getId())
                        .guestNumber(1)
                        .keyword("가락관광호텔")
                        .areaCode(SEOUL)
                        .checkIn("20241120")
                        .checkOut("20241123")
                        .roomTypeId(dummyRoom(dummyAccommodation()).getRoomTypeId())
                        .build();
        CartCreateResponse cartCreateResponse = new CartCreateResponse(1L);

        given(tourAPIService.bringAccommodation(any(), any())).willReturn(dummyAccommodation());
        given(tourAPIService.bringRoom(anyLong(), anyLong())).willReturn(dummyRoom(dummyAccommodation()));
        given(roomRepository.findByRoomTypeId(any())).willReturn(Optional.ofNullable(dummyRoom(dummyAccommodation())));
        given(accommodationRepository.findById(any())).willReturn(Optional.ofNullable(dummyAccommodation()));
        given(memberRepository.findById(any())).willReturn(Optional.ofNullable(dummyMember()));
        given(cartRepository.save(any(Cart.class))).willReturn(dummyCart());


        // when
        CartCreateResponse response =
                cartService.createCart(dummyMember().getId(), cartCreateRequest);

        // then
        assertEquals(response, cartCreateResponse);

    }

    @Test
    @DisplayName("장바구니 조회")
    void getMyCarts_success() {
        // given
        List<Cart> cartList = Arrays.asList(dummyCart(), dummyCart());
        List<CartGetResponse> cartGetResponseList = cartList.stream()
                .map((Cart cart) -> CartGetResponse.fromEntity(
                        cart, cart.getRoom(), cart.getRoom().getAccommodation())
                ).toList();

        given(cartRepository.findALLByMemberId(anyLong())).willReturn(cartList);

        // when
        List<CartGetResponse> response = cartService.getMyCarts(dummyCart().getId());

        // then
        assertEquals(cartGetResponseList, response);
        then(cartRepository).should().findALLByMemberId(dummyCart().getId());

    }

    @Test
    @DisplayName("장바구니 삭제")
    void deleteCart_success() {
        // given
        given(cartRepository.findById(anyLong())).willReturn(Optional.ofNullable(dummyCart()));

        // when
        Long actual = cartService.deleteCart(dummyCart().getId()).id();

        // then
        Long expected = dummyCart().getId();
        assertEquals(actual, expected);
    }
}