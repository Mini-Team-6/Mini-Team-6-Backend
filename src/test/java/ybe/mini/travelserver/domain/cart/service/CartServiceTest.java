//package ybe.mini.travelserver.domain.cart.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Profile;
//import org.springframework.transaction.annotation.Transactional;
//import ybe.mini.travelserver.domain.accommodation.entity.Location;
//import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
//import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
//import ybe.mini.travelserver.domain.cart.dto.request.CartCreateRequest;
//import ybe.mini.travelserver.domain.cart.dto.response.CartCreateResponse;
//import ybe.mini.travelserver.domain.cart.dto.response.CartGetResponse;
//import ybe.mini.travelserver.domain.cart.entity.Cart;
//import ybe.mini.travelserver.domain.cart.repository.CartRepository;
//import ybe.mini.travelserver.domain.member.entity.Member;
//import ybe.mini.travelserver.domain.member.repository.MemberRepository;
//import ybe.mini.travelserver.domain.room.entity.Room;
//import ybe.mini.travelserver.domain.room.repository.RoomRepository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//@Transactional
//@Profile("test")
//class CartServiceTest {
//
//    @Autowired
//    CartService cartService;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    CartRepository cartRepository;
//
//    @Autowired
//    RoomRepository roomRepository;
//
//    @Autowired
//    AccommodationRepository accommodationRepository;
//
//    @Test
//    void createCart_success(){
//
//        //given
//        Member member = createMember("test@test.com");
//        Location location = createLocation();
//        Accommodation createdAccommodation = createAccommodation("숙소A", location);
//        Room createdRoom = createRoom("101호", createdAccommodation);
//
//        CartCreateRequest createRequest =
//                CartCreateRequest.builder()
//                        .roomId(createdRoom.getId())
//                        .checkIn(LocalDateTime.of(2022,1,1,15,0))
//                        .checkOut(LocalDateTime.of(2022,1,2,11,0))
//                        .guestNumber(2)
//                        .build();
//
//        //when
//        final CartCreateResponse createdCart = cartService.createCart(member.getId(), createRequest);
//
//        //then
//        Cart findCart =
//                cartRepository.findById(createdCart.id()).orElseThrow(RuntimeException::new);
//        assertThat(findCart.getMember().getId()).isEqualTo(member.getId());
//        assertThat(findCart.getRoom().getName()).isEqualTo(createdRoom.getName());
//        assertThat(findCart.getRoom().getAccommodation().getName())
//                .isEqualTo(createdAccommodation.getName());
//        assertThat(findCart.getGuestNumber()).isEqualTo(createRequest.guestNumber());
//    }
//
//    @Test
//    void getMyCarts_success() {
//        //given
//        Member member1 = createMember("test1@test.com");
//        Member member2 = createMember("test2@test.com");
//        Location location = createLocation();
//        Accommodation createdAccommodation = createAccommodation("숙소A", location);
//        Room createdRoom = createRoom("101호", createdAccommodation);
//        CartCreateRequest createRequest =
//                CartCreateRequest.builder()
//                        .roomId(createdRoom.getId())
//                        .checkIn(LocalDateTime.of(2022,1,1,15,0))
//                        .checkOut(LocalDateTime.of(2022,1,2,11,0))
//                        .guestNumber(2)
//                        .build();
//        cartService.createCart(member1.getId(), createRequest);
//        cartService.createCart(member1.getId(), createRequest);
//        cartService.createCart(member2.getId(), createRequest);
//        cartService.createCart(member2.getId(), createRequest);
//
//        //when
//        final List<CartGetResponse> cartResponses = cartService.getMyCarts(member1.getId());
//
//        //then
//        assertThat(cartResponses.size()).isEqualTo(2);
//        assertThat(cartResponses.get(0).roomGetResponse().name()).isEqualTo(createdRoom.getName());
//    }
//
//    @Test
//    void deleteCart_success() {
//        //given
//        Member member = createMember("test1@test.com");
//        Location location = createLocation();
//        Accommodation createdAccommodation = createAccommodation("숙소A", location);
//        Room createdRoom = createRoom("101호", createdAccommodation);
//        CartCreateRequest createRequest =
//                CartCreateRequest.builder()
//                        .roomId(createdRoom.getId())
//                        .checkIn(LocalDateTime.of(2022,1,1,15,0))
//                        .checkOut(LocalDateTime.of(2022,1,2,11,0))
//                        .guestNumber(2)
//                        .build();
//        CartCreateResponse createdCart = cartService.createCart(member.getId(), createRequest);
//
//        //when
//        cartService.deleteCart(createdCart.id());
//
//        //then
//        assertThrows(
//            RuntimeException.class,
//            () -> cartRepository.findById(createdCart.id()).orElseThrow(RuntimeException::new)
//        );
//
//    }
//
//    private Member createMember(String email) {
//        return memberRepository.save(Member.builder()
//                        .email(email)
//                        .password("test")
//                        .name("test")
//                        .build());
//    }
//
//    private Room createRoom(String name, Accommodation accommodation) {
//        return roomRepository.save(Room.builder()
//                        .roomTypeId(1L)
//                        .name(name)
//                        .description("AAAAAAAAAAAA")
//                        .price(1000)
//                        .image("dummy-img")
//                        .stock(5)
//                        .capacity(5)
//                        .accommodation(accommodation)
//                        .build());
//    }
//
//    private Accommodation createAccommodation(String name, Location location) {
//        return accommodationRepository.save(Accommodation.builder()
//                        .id(1L)
//                        .name(name)
//                        .location(location)
//                        .description("숙소설명")
//                        .image("이미지")
//                        .build());
//    }
//
//    private Location createLocation() {
//        return Location.builder()
//                        .address("강원특별자치도 강릉시 창해로 307 ")
//                        .phone("033-660-9000")
//                        .areaCode("32")
//                        .latitude(37.7940780970)
//                        .longitude(128.9186301059)
//                        .build();
//    }
//}