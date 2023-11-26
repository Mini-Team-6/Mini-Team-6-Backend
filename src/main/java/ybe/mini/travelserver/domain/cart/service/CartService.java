package ybe.mini.travelserver.domain.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.cart.dto.request.CartCreateRequest;
import ybe.mini.travelserver.domain.cart.dto.response.CartCreateResponse;
import ybe.mini.travelserver.domain.cart.dto.response.CartDeleteResponse;
import ybe.mini.travelserver.domain.cart.dto.response.CartGetResponse;
import ybe.mini.travelserver.domain.cart.entity.Cart;
import ybe.mini.travelserver.domain.cart.repository.CartRepository;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public CartCreateResponse createCart(Long userId, CartCreateRequest createRequest) {
        Member member = getMemberById(userId);
        Room room = getRoomById(createRequest.roomId());

        Cart cart = Cart.builder()
                .member(member)
                .room(room)
                .checkIn(createRequest.checkIn())
                .checkOut(createRequest.checkOut())
                .guestNumber(createRequest.guestNumber())
                .build();
        Cart createdCart=  cartRepository.save(cart);
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
        cartRepository.deleteById(cartId);
        return new CartDeleteResponse(cartId);
    }

    private Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(RuntimeException::new);    //To-do : Custom 예외 처리 구현
    }

    private Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
