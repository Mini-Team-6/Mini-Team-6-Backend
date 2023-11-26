package ybe.mini.travelserver.domain.reservation.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateResponse;
import ybe.mini.travelserver.domain.reservation.dto.ReservationGetResponse;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation.repository.ReservationRepository;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;
import ybe.mini.travelserver.domain.reservation_room.repository.ReservationRoomRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ybe.mini.travelserver.domain.reservation.entity.ReservationStatus.PAYED_BEFORE;
import static ybe.mini.travelserver.domain.reservation.entity.ReservationStatus.PAYED_SUCCESS;
import static ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus.RESERVED;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ReservationRoomRepository reservationRoomRepository;

    @Test
    void createReservation_success() {
        //given
        Member member = createMember("test@test.com");
        ReservationCreateRequest reservationCreateRequest = createReservationCreateRequest();

        //when
        ReservationCreateResponse createdReservation =
                reservationService.createReservation(member.getEmail(), reservationCreateRequest);

        //then
        Reservation findReservation = reservationRepository.findById(createdReservation.id())
                .orElseThrow(RuntimeException::new);
        assertThat(findReservation.getStatus()).isEqualTo(PAYED_BEFORE);
        assertThat(findReservation.getReservationRooms().get(0).getCheckIn()).isEqualTo(LocalDateTime.of(2022,1,1,1,1));
        assertThat(findReservation.getReservationRooms().get(0).getCheckOut()).isEqualTo(LocalDateTime.of(2022,1,3,1,1));
        assertThat(findReservation.getReservationRooms().get(0).getStatus()).isEqualTo(RESERVED);
        assertThat(findReservation.getReservationRooms().get(0).getRoom().getRoomTypeId())
                .isEqualTo(reservationCreateRequest.reservationRooms().get(0).roomTypeId());
        assertThat(findReservation.getReservationRooms().get(0).getRoom().getAccommodation().getId())
                .isEqualTo(reservationCreateRequest.reservationRooms().get(0).accommodationId());
//        assertThat(findReservation.getReservationRooms().get(1).getCheckIn()).isEqualTo(LocalDateTime.of(2023,1,1,1,1));
//        assertThat(findReservation.getReservationRooms().get(1).getCheckOut()).isEqualTo(LocalDateTime.of(2023,1,3,1,1));
//        assertThat(findReservation.getReservationRooms().get(1).getStatus()).isEqualTo(RESERVED);

    }

    @Test
    void updateReservationStatusToPay_success() {
        //given
        Member member = createMember("test@test.com");
        ReservationCreateRequest reservationCreateRequest = createReservationCreateRequest();
        ReservationCreateResponse createdReservation =
                reservationService.createReservation(member.getEmail(), reservationCreateRequest);

        //when
        reservationService.updateReservationStatusToPay(createdReservation.id());

        //then
        Reservation findReservation = reservationRepository.findById(createdReservation.id())
                .orElseThrow(RuntimeException::new);
        assertThat(findReservation.getStatus()).isEqualTo(PAYED_SUCCESS);

    }

    @Test
    void getMyReservations_success() {
        //given
        Member member1 = createMember("test@test.com");
        ReservationCreateRequest reservationCreateRequest1 = createReservationCreateRequest();
        reservationService.createReservation(member1.getEmail(), reservationCreateRequest1);
        reservationService.createReservation(member1.getEmail(), reservationCreateRequest1);

        //when
//        List<Reservation> myReservations1 = reservationService.getMyReservations(member1.getEmail());
        List<ReservationGetResponse> myReservations1 = reservationService.getMyReservations(member1.getId());

        //then
        assertThat(myReservations1.size()).isEqualTo(2);
    }

    @Test
    void deleteReservation_success() {
        //given
        Member member = createMember("test@test.com");
        ReservationCreateRequest reservationCreateRequest = createReservationCreateRequest();
        ReservationCreateResponse reservation = reservationService.createReservation(member.getEmail(), reservationCreateRequest);

        //when
        reservationService.deleteReservation(reservation.id());

        //then
        Assertions.assertThrows(
                RuntimeException.class,
                () -> reservationRepository.findById(reservation.id()).orElseThrow(RuntimeException::new));

    }

    private ReservationCreateRequest createReservationCreateRequest() {
        ReservationRoomCreateRequest roomRequest1 =
                new ReservationRoomCreateRequest(
                        142785L, "가락관광호텔", 11430L,
                        LocalDateTime.of(2022,1,1,1,1),
                        LocalDateTime.of(2022,1,3,1,1), 2
                );
//        ReservationRoomCreateRequest roomRequest2 =
//                new ReservationRoomCreateRequest(
//                        2572777L, "게더링앳홍대", 32L,
//                        LocalDateTime.of(2023,1,1,1,1),
//                        LocalDateTime.of(2023,1,3,1,1), 2
//                );
//        return new ReservationCreateRequest(List.of(roomRequest1, roomRequest2));
        return new ReservationCreateRequest(List.of(roomRequest1));
    }

    private Member createMember(String email) {
        return memberRepository.save(Member.builder()
                .email(email)
                .password("test")
                .name("test")
                .build());
    }

}