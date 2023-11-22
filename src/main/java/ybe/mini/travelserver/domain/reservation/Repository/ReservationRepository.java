package ybe.mini.travelserver.domain.reservation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ybe.mini.travelserver.domain.reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
