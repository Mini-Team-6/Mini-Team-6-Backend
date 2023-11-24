package ybe.mini.travelserver.domain.accommodation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public List<AccommodationGetResponse> bringAccommodations(String keyword) {
        List<Accommodation> accommodations = accommodationRepository.findByNameContaining(keyword);

        return getResponseList(accommodations);
    }

    public List<AccommodationGetResponse> bringAccommodationsByAreaCode(String areaCode) {
        List<Accommodation> accommodations = accommodationRepository.findByLocationAreaCode(areaCode);

        return getResponseList(accommodations);
    }

    private static List<AccommodationGetResponse> getResponseList(List<Accommodation> accommodations) {
        return accommodations.stream()
                .map(AccommodationGetResponse::fromEntity)
                .collect(Collectors.toList());
    }

}

