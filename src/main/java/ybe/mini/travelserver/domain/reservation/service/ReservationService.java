package ybe.mini.travelserver.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
import ybe.mini.travelserver.domain.cart.entity.Cart;
import ybe.mini.travelserver.domain.cart.exception.CartNotFoundException;
import ybe.mini.travelserver.domain.cart.repository.CartRepository;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.member.exception.MemberNotFoundException;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateFromCartRequest;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateResponse;
import ybe.mini.travelserver.domain.reservation.dto.ReservationGetResponse;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation.exception.ReservationNotFoundException;
import ybe.mini.travelserver.domain.reservation.exception.RoomStockIsEmptyException;
import ybe.mini.travelserver.domain.reservation.repository.ReservationRepository;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;
import ybe.mini.travelserver.domain.reservation_room.repository.ReservationRoomRepository;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ybe.mini.travelserver.global.util.Validation.validateDateFormat;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationRoomRepository reservationRoomRepository;
    private final MemberRepository memberRepository;
    private final TourAPIService tourAPIService;
    private final RoomRepository roomRepository;
    private final AccommodationRepository accommodationRepository;
    private final CartRepository cartRepository;

    @Transactional
    public ReservationCreateResponse createReservation(String userEmail, ReservationCreateRequest reservationRequest) {


        List<ReservationRoom> reservationRooms =
                reservationRoomDtosToEntityList(reservationRequest.reservationRooms());

        reservationRooms.forEach(this::isEnableReservation);

        Member member = getMemberByEmail(userEmail);
        Reservation reservation =
                Reservation.createReservation(member, reservationRequest.paymentType(), reservationRooms);

        return ReservationCreateResponse.fromEntity(reservationRepository.save(reservation));
    }

    private void isEnableReservation(ReservationRoom resRoom) {
        int restStock = getRestStock(
                resRoom.getRoom(), resRoom.getCheckIn(), resRoom.getCheckOut()
        );
        if (restStock == 0) throw new RoomStockIsEmptyException();
    }

    private Integer getRestStock(Room room, LocalDate checkIn, LocalDate checkOut) {
        List<ReservationRoom> reservationRooms =
                reservationRoomRepository.findAllByRoomAndCheckInBetweenAndCheckOutBetween(
                        room, checkIn, checkOut, checkIn, checkOut
                );
        return Math.max(0, room.getStock() - reservationRooms.size());

    }

    public ReservationCreateResponse createReservationAndDeleteCart(
            String userEmail, ReservationCreateFromCartRequest reservationRequest
    ) {

        reservationRequest.cartIds().forEach(this::deleteCart);

        List<ReservationRoom> reservationRooms =
                reservationRoomDtosToEntityList(reservationRequest.reservationRooms());
        Member member = getMemberByEmail(userEmail);
        Reservation reservation =
                Reservation.createReservation(member, reservationRequest.paymentType(), reservationRooms);

        return ReservationCreateResponse.fromEntity(reservationRepository.save(reservation));
    }

    @Transactional(readOnly = true)
    public List<ReservationGetResponse> getMyReservations(Long memberId, Pageable pageable) {
        Page<Reservation> reservations = reservationRepository.findAllByMemberId(memberId, pageable);
        int totalPage = reservations.getTotalPages();

        return reservations.stream()
                .map(reservation -> ReservationGetResponse.fromEntity(reservation, totalPage)).toList();
    }

    @Transactional
    public Long deleteReservation(Long reservationId) {
        getReservationById(reservationId);
        reservationRepository.deleteById(getReservationById(reservationId).getId());
        return reservationId;
    }

    private List<ReservationRoom> reservationRoomDtosToEntityList(List<ReservationRoomCreateRequest> roomCreateRequests) {
        List<ReservationRoom> reservationRooms = new ArrayList<>();
        for (ReservationRoomCreateRequest roomRequest : roomCreateRequests) {
            ReservationRoom reservationRoom = reservationRoomDtoToEntity(roomRequest);
            reservationRooms.add(reservationRoom);
        }
        return reservationRooms;
    }

    private ReservationRoom reservationRoomDtoToEntity(ReservationRoomCreateRequest roomRequest) {

        String areaCodeString =
                (roomRequest.areaCode() != null) ? String.valueOf(roomRequest.areaCode().getCode()) : null;

        Accommodation accommodation =
                tourAPIService.bringAccommodation(roomRequest.accommodationName(), areaCodeString);
        Room room = tourAPIService.bringRoom(roomRequest.accommodationId(), roomRequest.roomTypeId());

        accommodation = getOrSaveAccommodation(accommodation);
        room.setAccommodation(accommodation);
        room = getOrSaveRoom(room);

        return ReservationRoom.createReservationRoom(
                room,
                validateDateFormat(roomRequest.checkIn()),
                validateDateFormat(roomRequest.checkOut()),
                roomRequest.guestNumber()
        );
    }

    private Room getOrSaveRoom(Room room) {
        return roomRepository.findByRoomTypeId(room.getRoomTypeId())
                .orElseGet(() -> roomRepository.save(room));
    }

    private Accommodation getOrSaveAccommodation(Accommodation accommodation) {
        return accommodationRepository.findById(accommodation.getId())
                .orElseGet(() -> accommodationRepository.save(accommodation));
    }

    private Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(ReservationNotFoundException::new);
    }

    private Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }

    private void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        cartRepository.deleteById(cart.getId());
    }

}
