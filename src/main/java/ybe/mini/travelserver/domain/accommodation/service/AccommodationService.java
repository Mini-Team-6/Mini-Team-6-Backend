package ybe.mini.travelserver.domain.accommodation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationDetailGetResponse;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.entity.AreaCode;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final TourAPIService tourAPIService;

    public List<AccommodationGetResponse> bringAccommodations(
            int pageNo, int numOfRows,
            String keyword, AreaCode areaCode
    ) {
        if (pageNo <= 0) return Collections.emptyList();

        String areaCodeString = (areaCode != null) ? String.valueOf(areaCode.getCode()) : null;

        List<Accommodation> accommodations =
                tourAPIService.bringAccommodations(
                        pageNo,
                        numOfRows,
                        keyword,
                        areaCodeString);

        return accommodations.stream()
                .map(AccommodationGetResponse::fromEntity)
                .toList();
    }

    public AccommodationDetailGetResponse bringAccommodationFromAPI(String keyword, AreaCode areaCode) {
        String areaCodeString = (areaCode != null) ? String.valueOf(areaCode.getCode()) : null;

        Accommodation accommodation =
                tourAPIService.bringAccommodation(keyword, areaCodeString);
        return AccommodationDetailGetResponse.fromEntity(accommodation);
    }

}

