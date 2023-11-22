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

    public List<AccommodationGetResponse> getAllAccommodationsByKeyword(String keyword) {
        return accommodationRepository.findAllByNameContaining(keyword);
    }

    public List<AccommodationGetResponse> getAccommodationsByCityAndDistrict(String city, String district) {
        List<Accommodation> accommodations =
                accommodationRepository.findByLocationAddressContaining(city + " " + district);

        List<AccommodationGetResponse> responses = accommodations.stream()
                .map(AccommodationGetResponse::fromEntity)
                .collect(Collectors.toList());

        return responses;
    }
}

