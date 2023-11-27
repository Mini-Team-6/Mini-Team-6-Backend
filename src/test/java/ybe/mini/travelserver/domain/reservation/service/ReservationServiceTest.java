package ybe.mini.travelserver.domain.reservation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
import ybe.mini.travelserver.domain.member.dummy.DummyMember;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.domain.reservation.dummy.DummyReservation;
import ybe.mini.travelserver.domain.reservation.dummy.DummyReservationDTO;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation.repository.ReservationRepository;
import ybe.mini.travelserver.domain.room.DummyObjectForRoom;
import ybe.mini.travelserver.domain.room.entity.Room;
import ybe.mini.travelserver.domain.room.repository.RoomRepository;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest implements DummyObjectForRoom, DummyReservationDTO, DummyMember, DummyReservation {

    @Mock
    ReservationRepository reservationRepository;
    @Mock
    MemberRepository memberRepository;
    @Mock
    TourAPIService tourAPIService;
    @Mock
    RoomRepository roomRepository;
    @Mock
    AccommodationRepository accommodationRepository;

    @InjectMocks
    ReservationService reservationService;

    @Test
    void createReservation_success() {
        //given
        given(tourAPIService.bringAccommodation(any(), any())).willReturn(dummyAccommodation());
        given(tourAPIService.bringRoom(anyLong(), anyLong())).willReturn(dummyRoom(dummyAccommodation()));
        given(roomRepository.findByRoomTypeId(any())).willReturn(Optional.empty());
        given(roomRepository.save(any(Room.class))).willReturn(dummyRoom(dummyAccommodation()));
        given(accommodationRepository.findById(any())).willReturn(Optional.ofNullable(dummyAccommodation()));
        given(memberRepository.findByEmail(any())).willReturn(Optional.ofNullable(dummyMember()));
        given(reservationRepository.save(any(Reservation.class))).willReturn(dummyReservation());

        //when
        var actual = reservationService.createReservation(dummyMember().getEmail(), dummyReservationCreateReq());

        //then
        var expected = dummyReservationCreateRes();
        assertEquals(actual, expected);

    }

    @Test
    void deleteReservation_success() {
        //given
        given(reservationRepository.findById(anyLong())).willReturn(Optional.ofNullable(dummyReservation()));

        //when
        var actual = reservationService.deleteReservation(dummyReservation().getId());

        //then
        var expected = dummyReservation().getId();
        assertEquals(actual, expected);
    }
}