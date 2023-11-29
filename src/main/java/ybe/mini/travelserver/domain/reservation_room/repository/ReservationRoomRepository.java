package ybe.mini.travelserver.domain.reservation_room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoom;
import ybe.mini.travelserver.domain.room.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {

    List<ReservationRoom> findAllByReservation(Reservation reservation);

    List<ReservationRoom> findAllByRoomAndCheckInBetweenAndCheckOutBetween(
            Room room,
            LocalDate checkInDate1, LocalDate checkOutDate1,
            LocalDate checkInDate2, LocalDate checkOutDate2
    );
}
