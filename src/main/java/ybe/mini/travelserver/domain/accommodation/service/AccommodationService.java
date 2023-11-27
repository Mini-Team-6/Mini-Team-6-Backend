package ybe.mini.travelserver.domain.accommodation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationDetailGetResponse;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.entity.AreaCode;
import ybe.mini.travelserver.domain.accommodation.repository.AccommodationRepository;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final TourAPIService tourAPIService;

    public List<AccommodationGetResponse> bringAccommodations(
            int pageNo, int numOfRows,
            String keyword, AreaCode areaCode
    ) {
        String areaCodeString = (areaCode != null) ? String.valueOf(areaCode.getCode()) : null;

        List<Accommodation> accommodations =
                tourAPIService.bringAccommodations(
                        pageNo,
                        numOfRows,
                        keyword,
                        areaCodeString);

        return getResponseList(accommodations);
    }

    public AccommodationDetailGetResponse bringAccommodationFromAPI(String keyword, AreaCode areaCode) {
        String areaCodeString = (areaCode != null) ? String.valueOf(areaCode.getCode()) : null;

        Accommodation accommodation =
                tourAPIService.bringAccommodation(keyword, areaCodeString);
        return AccommodationDetailGetResponse.fromEntity(accommodation);
    }

    @Transactional(readOnly = true)
    public List<AccommodationGetResponse> bringAccommodations(String keyword, String areaCode) {
        List<Accommodation> accommodations;

        if (!Objects.isNull(keyword) && !Objects.isNull(areaCode)) {
            accommodations = accommodationRepository.findByNameContainingAndLocationAreaCode(keyword, areaCode);
        } else if (!Objects.isNull(keyword)) {
            accommodations = accommodationRepository.findByNameContaining(keyword);
        } else if (!Objects.isNull(areaCode)) {
            accommodations = accommodationRepository.findByLocationAreaCode(areaCode);
        } else {
            accommodations = accommodationRepository.findAll();
        }

        return getResponseList(accommodations);
    }


    private Accommodation bringAccommodation(Long accommodationId) {
        return accommodationRepository
                .findById(accommodationId).orElseThrow(RuntimeException::new);
    }

    private static List<AccommodationGetResponse> getResponseList(List<Accommodation> accommodations) {
        return accommodations.stream()
                .map(AccommodationGetResponse::fromEntity)
                .toList();
    }

}

