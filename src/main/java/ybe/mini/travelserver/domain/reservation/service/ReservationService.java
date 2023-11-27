package ybe.mini.travelserver.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
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
import ybe.mini.travelserver.domain.reservation.repository.ReservationRepository;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final TourAPIService tourAPIService;
    private final RoomRepository roomRepository;
    private final AccommodationRepository accommodationRepository;
    private final CartRepository cartRepository;

    @Transactional
    public ReservationCreateResponse createReservation(String userEmail, ReservationCreateRequest reservationRequest) {

        List<ReservationRoom> reservationRooms =
                reservationRoomDtosToEntityList(reservationRequest.reservationRooms());
        Member member = getMemberByEmail(userEmail);
        Reservation reservation =
                Reservation.createReservation(member, reservationRequest.paymentType(), reservationRooms);

        return ReservationCreateResponse.fromEntity(reservationRepository.save(reservation));
    }

    public ReservationCreateResponse createReservationAndDeleteCart(
            String userEmail, ReservationCreateFromCartRequest reservationRequest
    ) {

        reservationRequest.cardIds().forEach(this::deleteCart);

        List<ReservationRoom> reservationRooms =
                reservationRoomDtosToEntityList(reservationRequest.reservationRooms());
        Member member = getMemberByEmail(userEmail);
        Reservation reservation =
                Reservation.createReservation(member, reservationRequest.paymentType(), reservationRooms);

        return ReservationCreateResponse.fromEntity(reservationRepository.save(reservation));
    }

    @Transactional(readOnly = true)
    public List<ReservationGetResponse> getMyReservations(Long memberId) {
        return reservationRepository.findAllByMemberId(memberId).stream()
                .map(ReservationGetResponse::fromEntity).toList();
    }

    @Transactional
    public Long deleteReservation(Long reservationId) {
        getReservationById(reservationId);
        reservationRepository.deleteById(getReservationById(reservationId).getId());
        return reservationId;
    }

    private List<ReservationRoom> reservationRoomDtosToEntityList(List<ReservationRoomCreateRequest> roomCreateRequests) {
        List<ReservationRoom> reservationRooms = new ArrayList<>();
        for(ReservationRoomCreateRequest roomRequest : roomCreateRequests) {
            ReservationRoom reservationRoom = reservationRoomDtoToEntity(roomRequest);
            reservationRooms.add(reservationRoom);
        }
        return reservationRooms;
    }

    private ReservationRoom reservationRoomDtoToEntity(ReservationRoomCreateRequest roomRequest) {
        Accommodation accommodation =   //todo : Bring 제대로 안되면 Exception 날려주는 거 고민
                tourAPIService.bringAccommodation(roomRequest.accommodationName(), roomRequest.areaCode());
        Room room = tourAPIService.bringRoom(roomRequest.accommodationId(), roomRequest.roomTypeId());

        accommodation = getOrSaveAccommodation(accommodation);
        room.setAccommodation(accommodation);
        room = getOrSaveRoom(room);

        return ReservationRoom.createReservationRoom(
                room, roomRequest.checkIn(), roomRequest.checkOut(), roomRequest.guestNumber()
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
        cartRepository.findById(cartId).orElseThrow(RuntimeException::new);  //todo : CustomException, 민정님 구현 후 적용
        cartRepository.deleteById(cartId);
    }

}
