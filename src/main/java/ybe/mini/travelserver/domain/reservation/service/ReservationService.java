package ybe.mini.travelserver.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.domain.reservation.repository.ReservationRepository;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
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

    @Transactional
    public Reservation createReservation(String userEmail, ReservationCreateRequest reservationRequest) {

        List<ReservationRoom> reservationRooms = new ArrayList<>();
        for(ReservationRoomCreateRequest roomRequest : reservationRequest.reservationRooms()) {
            Accommodation accommodation =   //To-do : Bring 제대로 안되면 Exception 날려주는 거 고민
                    tourAPIService.bringAccommodation(roomRequest.accommodationId(), roomRequest.accommodationName());
            List<Room> rooms =
                    tourAPIService.bringRooms(roomRequest.accommodationId());

            Room room = null;
            for(Room tRoom : rooms) {
                if(tRoom.getRoomTypeId().equals(roomRequest.roomTypeId())) {
                    room = tRoom;
                }
            }
            if(room==null) {
                //To-do : Exception 처리
            }
            assert room != null;    //Exception 구현되는 대로 없앨 예정

            accommodation = getOrSaveAccommodation(accommodation);
            room.setAccommodation(accommodation);
            room = getOrSaveRoom(room);

            ReservationRoom reservationRoom = ReservationRoom.createReservationRoom(
                    room, roomRequest.checkIn(), roomRequest.checkOut(), roomRequest.guestNumber()
            );
            reservationRooms.add(reservationRoom);
        }

        Member member = getMemberByEmail(userEmail);

        Reservation reservation = Reservation.createReservation(member, reservationRooms);
        return reservationRepository.save(reservation);
    }

    @Transactional(readOnly = true)
    public List<Reservation> getMyReservations(Long memberId) {
        return reservationRepository.findAllByMemberId(memberId);
    }

    @Transactional
    public Long updateReservationStatusToPay(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);
        reservation.updateStatusToPaySuccess();
        return reservation.getId();
    }

    @Transactional
    public Long deleteReservation(Long reservationId) {
        reservationRepository.deleteById(getReservationById(reservationId).getId());
        return reservationId;
    }

    private Room getOrSaveRoom(Room room) {
        return roomRepository.findByRoomTypeId(room.getRoomTypeId())
                .orElseGet(() -> roomRepository.save(room));
    }

    private Accommodation getOrSaveAccommodation(Accommodation accommodation) {
        return accommodationRepository.findById(accommodation.getId())
                .orElseGet(() -> accommodationRepository.save(accommodation));
    }

    private Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    private Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(RuntimeException::new);    //To-do : Custom Exception Handle
    }

    private Member getMemberByEmail(String email) {
        log.info("TAGGGGGGGGGGGGGGGGGGGG = {}", email);
        return memberRepository.findByEmail(email)
                .orElseThrow(RuntimeException::new);    //To-do : Custom Exception Handle
    }

}
