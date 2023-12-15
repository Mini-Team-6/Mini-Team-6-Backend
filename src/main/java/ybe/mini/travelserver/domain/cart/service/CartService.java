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

import java.time.LocalDate;
import java.util.List;

import static ybe.mini.travelserver.global.util.Validation.validateDateFormat;

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
    public CartCreateResponse createCart(String email, CartCreateRequest cartCreateRequest) {
        Member member = getMemberEmail(email);
        Accommodation accommodation =
                createAccommodationById(cartCreateRequest.keyword(), cartCreateRequest.areaCode());
        Room room = createRoomByRoomTypeId(accommodation, cartCreateRequest.roomTypeId());

        Cart cart = createCart(cartCreateRequest, room, member);
        Cart createdCart = cartRepository.save(cart);

        return new CartCreateResponse(createdCart.getId());
    }

    @Transactional(readOnly = true)
    public List<CartGetResponse> getMyCarts(String email) {
        return cartRepository.findALLByMemberEmail(email).stream()
                .map((Cart cart) -> CartGetResponse.fromEntity(
                        cart, cart.getRoom(), cart.getRoom().getAccommodation())
                ).toList();
    }

    @Transactional
    public CartDeleteResponse deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(CartNotFoundException::new);
        cartRepository.deleteById(cart.getId());
        return new CartDeleteResponse(cart.getId());
    }

    private Member getMemberEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }

    private Accommodation createAccommodationById(String keyword, AreaCode areaCode) {
        String areaCodeString =
                (areaCode != null) ? String.valueOf(areaCode.getCode()) : null;
        Accommodation accommodation = tourAPIService.bringAccommodation(keyword, areaCodeString);
        return getOrSaveAccommodation(accommodation);
    }

    private Room createRoomByRoomTypeId(Accommodation accommodation, Long roomTypeId) {
        Room room = tourAPIService.bringRoom(accommodation.getId(), roomTypeId);
        return getOrSaveRoom(room);
    }

    private Cart createCart(CartCreateRequest cartCreateRequest, Room room, Member member) {

        LocalDate checkIn = validateDateFormat(cartCreateRequest.checkIn());
        LocalDate checkOut = validateDateFormat(cartCreateRequest.checkOut());
        return Cart.builder()
                .guestNumber(cartCreateRequest.guestNumber())
                .room(room)
                .member(member)
                .checkOut(checkOut)
                .checkIn(checkIn)
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