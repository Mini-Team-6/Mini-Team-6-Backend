package ybe.mini.travelserver.domain.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.entity.AreaCode;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
import ybe.mini.travelserver.domain.cart.dto.request.CartCreateRequest;
import ybe.mini.travelserver.domain.cart.dto.response.CartCreateResponse;
import ybe.mini.travelserver.domain.cart.dto.response.CartDeleteResponse;
import ybe.mini.travelserver.domain.cart.dto.response.CartGetResponse;
import ybe.mini.travelserver.domain.cart.entity.Cart;
import ybe.mini.travelserver.domain.cart.exception.CartNotFoundException;
import ybe.mini.travelserver.domain.cart.repository.CartRepository;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.member.exception.MemberNotFoundException;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final TourAPIService tourAPIService;
    private final AccommodationRepository accommodationRepository;

    @Transactional
    public CartCreateResponse createCart(Long userId, CartCreateRequest cartCreateRequest) {
        Member member = getMemberById(userId);
        Accommodation accommodation =
                createAccommodationById(cartCreateRequest.keyword(), cartCreateRequest.areaCode());
        Room room = createRoomById(accommodation, cartCreateRequest.roomTypeId());

        Cart cart = createCart(cartCreateRequest, room, member);
        Cart createdCart = cartRepository.save(cart);

        return new CartCreateResponse(createdCart.getId());
    }

    @Transactional(readOnly = true)
    public List<CartGetResponse> getMyCarts(Long userId) {
        return cartRepository.findALLByMemberId(userId).stream()
                .map((Cart cart) -> CartGetResponse.fromEntity(
                        cart, cart.getRoom(), cart.getRoom().getAccommodation())
                ).toList();
    }

    @Transactional
    public CartDeleteResponse deleteCart(Long cartId) {
        cartRepository.findById(cartId)
                .orElseThrow(CartNotFoundException::new);
        cartRepository.deleteById(cartId);
        return new CartDeleteResponse(cartId);
    }

    private Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    private Accommodation createAccommodationById(String keyword, AreaCode areaCode) {
        String areaCodeString =
                (areaCode != null) ? String.valueOf(areaCode.getCode()) : null;
        Accommodation accommodation = tourAPIService.bringAccommodation(keyword, areaCodeString);
        return getOrSaveAccommodation(accommodation);
    }

    private Room createRoomById(Accommodation accommodation, Long roomId) {
        Room room = tourAPIService.bringRoom(accommodation.getId(), roomId);
        return getOrSaveRoom(room);
    }

    private Cart createCart(CartCreateRequest cartCreateRequest, Room room, Member member) {
        return Cart.builder()
                .guestNumber(cartCreateRequest.guestNumber())
                .room(room)
                .member(member)
                .checkOut(cartCreateRequest.checkOut())
                .checkIn(cartCreateRequest.checkIn())
                .build();
    }

    private Room getOrSaveRoom(Room room) {
        return roomRepository.findByRoomTypeId(room.getRoomTypeId())
                .orElseGet(() -> roomRepository.save(room));
    }

    private Accommodation getOrSaveAccommodation(Accommodation accommodation) {
        return accommodationRepository.findById(accommodation.getId())
                .orElseGet(() -> accommodationRepository.save(accommodation));
    }

}