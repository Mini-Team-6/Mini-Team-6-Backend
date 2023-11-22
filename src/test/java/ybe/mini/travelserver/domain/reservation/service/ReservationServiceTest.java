package ybe.mini.travelserver.domain.reservation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.domain.reservation.Repository.ReservationRepository;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation.entity.ReservationStatus;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ybe.mini.travelserver.domain.reservation.entity.ReservationStatus.PAYED_BEFORE;
import static ybe.mini.travelserver.domain.reservation.entity.ReservationStatus.PAYED_SUCCESS;
import static ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus.RESERVED;

@SpringBootTest
@Transactional
class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void createReservation_success() {
        //given
        Member member = createMember();
        ReservationCreateRequest reservationCreateRequest = createReservationCreateRequest();

        //when
        Reservation createdReservation =
                reservationService.createReservation(member.getEmail(), reservationCreateRequest);

        //then
        Reservation findReservation = reservationRepository.findById(createdReservation.getId())
                .orElseThrow(RuntimeException::new);
        assertThat(findReservation.getStatus()).isEqualTo(PAYED_BEFORE);
        assertThat(findReservation.getReservationRooms().get(0).getCheckIn()).isEqualTo(LocalDateTime.of(2022,1,1,1,1));
        assertThat(findReservation.getReservationRooms().get(0).getCheckOut()).isEqualTo(LocalDateTime.of(2022,1,3,1,1));
        assertThat(findReservation.getReservationRooms().get(0).getStatus()).isEqualTo(RESERVED);

        assertThat(findReservation.getReservationRooms().get(1).getCheckIn()).isEqualTo(LocalDateTime.of(2023,1,1,1,1));
        assertThat(findReservation.getReservationRooms().get(1).getCheckOut()).isEqualTo(LocalDateTime.of(2023,1,3,1,1));
        assertThat(findReservation.getReservationRooms().get(1).getStatus()).isEqualTo(RESERVED);

    }

    @Test
    void updateReservationStatusToPay_success() {
        //given
        Member member = createMember();
        ReservationCreateRequest reservationCreateRequest = createReservationCreateRequest();
        Reservation createdReservation =
                reservationService.createReservation(member.getEmail(), reservationCreateRequest);

        //when
        reservationService.updateReservationStatusToPay(createdReservation.getId());

        //then
        Reservation findReservation = reservationRepository.findById(createdReservation.getId())
                .orElseThrow(RuntimeException::new);
        assertThat(findReservation.getStatus()).isEqualTo(PAYED_SUCCESS);

    }

    private ReservationCreateRequest createReservationCreateRequest() {
        ReservationRoomCreateRequest roomRequest1 =
                new ReservationRoomCreateRequest(
                        1L, LocalDateTime.of(2022,1,1,1,1),
                        LocalDateTime.of(2022,1,3,1,1), 2
                );
        ReservationRoomCreateRequest roomRequest2 =
                new ReservationRoomCreateRequest(
                        1L, LocalDateTime.of(2023,1,1,1,1),
                        LocalDateTime.of(2023,1,3,1,1), 2
                );
        return new ReservationCreateRequest(List.of(roomRequest1, roomRequest2));
    }

    private Member createMember() {
        return memberRepository.save(Member.builder()
                .email("test@test.com")
                .password("test")
                .name("test")
                .build());
    }

}