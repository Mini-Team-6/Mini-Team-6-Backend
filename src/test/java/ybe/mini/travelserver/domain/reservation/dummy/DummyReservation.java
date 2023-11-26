package ybe.mini.travelserver.domain.reservation.dummy;

import ybe.mini.travelserver.domain.member.dummy.DummyMember;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;
import ybe.mini.travelserver.domain.reservation.entity.ReservationStatus;

import java.time.LocalDateTime;

public interface DummyReservation extends DummyMember {

    default Reservation dummyReservation() {
        return Reservation.builder()
                .id(1L)
                .member(dummyMember())
                .status(ReservationStatus.PAYED_BEFORE)
                .createdAt(LocalDateTime.of(2022,1,1,0,0))
                .build();
    }
}
