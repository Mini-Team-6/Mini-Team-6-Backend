package ybe.mini.travelserver.domain.reservation_room.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation.repository.ReservationRepository;
import ybe.mini.travelserver.domain.reservation.service.ReservationService;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;
import ybe.mini.travelserver.domain.reservation_room.repository.ReservationRoomRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ReservationRoomServiceTest {

    @Autowired
    ReservationRoomService reservationRoomService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ReservationRoomRepository reservationRoomRepository;

    @Test
    void getReservationRoomsFromReservation_success() {
        //given
        Member member = createMember("test@test.com");
        ReservationCreateRequest reservationCreateRequest = createReservationCreateRequest();

        //when
        Reservation createdReservation =
                reservationService.createReservation(member.getEmail(), reservationCreateRequest);
        List<ReservationRoom> getRooms =
                reservationRoomService.getReservationRoomsFromReservation(createdReservation.getId());

        //then
        List<ReservationRoom> createdRooms = createdReservation.getReservationRooms();
        assertThat(createdRooms.size()).isEqualTo(getRooms.size());
        for(int i=0; i<createdRooms.size(); i++) {
            assertThat(createdRooms.get(i).getId()).isEqualTo(getRooms.get(i).getId());
        }
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

    private Member createMember(String email) {
        return memberRepository.save(Member.builder()
                .email(email)
                .password("test")
                .name("test")
                .build());
    }


}