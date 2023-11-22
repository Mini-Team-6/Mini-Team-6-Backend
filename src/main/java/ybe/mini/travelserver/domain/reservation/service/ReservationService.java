package ybe.mini.travelserver.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.domain.reservation.Repository.ReservationRepository;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Reservation createReservation(String userEmail, ReservationCreateRequest reservationRequest) {
        Member member = getMemberByEmail(userEmail);
        List<ReservationRoom> reservationRooms = reservationRequest.reservationRooms()
                .stream().map(reservationRoom -> ReservationRoom.createReservationRoom(
                null, reservationRoom.checkIn(), reservationRoom.checkOut(), reservationRoom.guestNumber()
        )).toList();    //to-do : null 대신 Room 넣기

        Reservation reservation = Reservation.createReservation(member, reservationRooms);
        return reservationRepository.save(reservation);
    }

    @Transactional(readOnly = true)
    public List<Reservation> getMyReservations(String userEmail) {
        Member member = getMemberByEmail(userEmail);
        return reservationRepository.findByMember(member);
    }

    @Transactional
    public Long updateReservationStatusToPay(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);
        reservation.updateStatusToPaySuccess();
        return reservation.getId();
    }

    @Transactional
    public Long deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
        return reservationId;
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(RuntimeException::new);    //To-do : Custom Exception Handle
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(RuntimeException::new);    //To-do : Custom Exception Handle
    }

}
