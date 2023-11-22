package ybe.mini.travelserver.global.api;

import org.springframework.data.jpa.repository.JpaRepository;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
