package ybe.mini.travelserver.domain.reservation_room.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.reservation.repository.ReservationRepository;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;
import ybe.mini.travelserver.domain.reservation_room.repository.ReservationRoomRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationRoomService {

    private final ReservationRoomRepository reservationRoomRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public List<ReservationRoom> getReservationRoomsFromReservation(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);

        return reservationRoomRepository.findAllByReservation(reservation);

    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
