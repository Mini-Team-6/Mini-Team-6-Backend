package ybe.mini.travelserver.domain.accommodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findByNameContainingAndLocationAreaCode(String keyword, String areaCode);

    List<Accommodation> findByNameContaining(String keyword);

    List<Accommodation> findByLocationAreaCode(String areaCode);

}

