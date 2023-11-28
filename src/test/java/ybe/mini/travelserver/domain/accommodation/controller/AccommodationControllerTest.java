package ybe.mini.travelserver.domain.accommodation.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.domain.accommodation.DummyAccommodation;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationDetailGetResponse;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.AreaCode;
import ybe.mini.travelserver.domain.accommodation.service.AccommodationService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AccommodationControllerTest extends DummyAccommodation {
    @Mock
    AccommodationService accommodationService;
    @InjectMocks
    AccommodationController accommodationController;


    @DisplayName("숙소 리스트 조회")
    @Test
    void SearchAccommodations_success() {
        // given
        List<AccommodationGetResponse> expectedAccommodations = Arrays.asList(
                AccommodationGetResponse.fromEntity(dummyAccommodation()),
                AccommodationGetResponse.fromEntity(dummyAccommodation1())
        );
        given(accommodationService.bringAccommodations(1, 10, "호텔", AreaCode.SEOUL))
                .willReturn(expectedAccommodations);

        // when
        ResponseDto<List<AccommodationGetResponse>> responseDto =
                accommodationController.searchAccommodations(1, "호텔", AreaCode.SEOUL);

        // then
        assertNotNull(responseDto);
        assertEquals(HttpStatus.OK.value(), responseDto.status());
        assertEquals(expectedAccommodations, responseDto.data());
        then(accommodationService).should().bringAccommodations(1, 10, "호텔", AreaCode.SEOUL);

    }

    @DisplayName("숙소 상세 조회")
    @Test
    void SearchAccommodation_success() {
        // given
        AccommodationDetailGetResponse expectedAccommodation =
                AccommodationDetailGetResponse.fromEntity(dummyAccommodation());
        given(accommodationService.bringAccommodationFromAPI("호텔", AreaCode.SEOUL))
                .willReturn(expectedAccommodation);

        // when
        ResponseDto<AccommodationDetailGetResponse> responseDto =
                accommodationController.searchAccommodation("호텔", AreaCode.SEOUL);

        // then
        assertNotNull(responseDto);
        assertEquals(HttpStatus.OK.value(), responseDto.status());
        assertEquals(expectedAccommodation, responseDto.data());
        then(accommodationService).should().bringAccommodationFromAPI("호텔", AreaCode.SEOUL);
    }
}
