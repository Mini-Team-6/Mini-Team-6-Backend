package ybe.mini.travelserver.domain.accommodation.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ybe.mini.travelserver.domain.accommodation.DummyAccommodation;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationDetailGetResponse;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.entity.AreaCode;
import ybe.mini.travelserver.global.api.TourAPIService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccommodationServiceTest extends DummyAccommodation {
    @Mock
    TourAPIService tourAPIService;
    @InjectMocks
    AccommodationService accommodationService;

    @DisplayName("숙소 리스트 조회")
    @Test
    void SearchAccommodations_success() {
        // given
        List<Accommodation> expectedAccommodations = Arrays.asList(
                dummyAccommodation(), dummyAccommodation1()
        );
        List<AccommodationGetResponse> responseDto = expectedAccommodations.stream()
                .map(AccommodationGetResponse::fromEntity)
                .toList();
        given(tourAPIService.bringAccommodations(eq(1), eq(10), eq("호텔"), eq("1")))
                .willReturn(expectedAccommodations);

        // when
        List<AccommodationGetResponse> actualAccommodations =
                accommodationService.bringAccommodations(1, 10, "호텔", AreaCode.SEOUL);

        // then
        assertNotNull(actualAccommodations);
        assertEquals(responseDto.size(), actualAccommodations.size());
        assertEquals(responseDto, actualAccommodations);
        then(tourAPIService).should().bringAccommodations(eq(1), eq(10), eq("호텔"), eq("1"));

    }

    @DisplayName("숙소 상세 조회")
    @Test
    void SearchAccommodation_success() {
        // given
        Accommodation expectedAccommodation = dummyAccommodation();
        AccommodationDetailGetResponse responseDto =
                AccommodationDetailGetResponse.fromEntity(expectedAccommodation);
        given(tourAPIService.bringAccommodation(eq("호텔"), eq("1")))
                .willReturn(expectedAccommodation);

        // when
        AccommodationDetailGetResponse actualAccommodation =
                accommodationService.bringAccommodationFromAPI("호텔", AreaCode.SEOUL);

        // then
        assertNotNull(actualAccommodation);
        assertEquals(responseDto, actualAccommodation);
        then(tourAPIService).should().bringAccommodation(eq("호텔"), eq("1"));
    }
}
