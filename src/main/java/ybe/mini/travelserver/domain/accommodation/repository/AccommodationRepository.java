package ybe.mini.travelserver.domain.accommodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;

import java.util.List;

@Component("customAccommodationRepository")
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<AccommodationGetResponse> findAllByNameContaining(String keyword);
    List<Accommodation> findByLocationAddressContaining(String address);
}

