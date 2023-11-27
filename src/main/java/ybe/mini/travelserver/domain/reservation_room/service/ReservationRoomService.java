package ybe.mini.travelserver.domain.reservation_room.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.reservation.repository.ReservationRepository;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomGetResponse;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;
import ybe.mini.travelserver.domain.reservation_room.repository.ReservationRoomRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationRoomService {

    private final ReservationRoomRepository reservationRoomRepository;
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<ReservationRoomGetResponse> getReservationRoomsFromReservation(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);

        return reservationRoomRepository.findAllByReservation(reservation).stream()
                .map(ReservationRoomGetResponse::fromEntity).toList();
    }

    @Transactional
    public Long deleteReservationRoom(Long reservationId, Long reservationRoomId) {
        reservationRoomRepository.deleteById(getReservationRoomById(reservationRoomId).getId());
        getReservationById(reservationId).deleteReservationRoom(reservationRoomId);
        return reservationRoomId;
    }

    private Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    private ReservationRoom getReservationRoomById(Long id) {
        return reservationRoomRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
