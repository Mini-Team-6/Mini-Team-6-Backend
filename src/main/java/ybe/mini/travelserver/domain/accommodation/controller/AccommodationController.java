package ybe.mini.travelserver.domain.accommodation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationDetailGetResponse;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.AreaCode;
import ybe.mini.travelserver.domain.accommodation.service.AccommodationService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("/page/{pageNo}")
    public ResponseDto<List<AccommodationGetResponse>> searchAccommodations(
            @PathVariable int pageNo,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, value = "area-code") AreaCode areaCode
    ) {
        int numOfRows = 10;
        List<AccommodationGetResponse> accommodations =
                accommodationService.bringAccommodations(pageNo, numOfRows, keyword, areaCode);
        return new ResponseDto<>(HttpStatus.OK.value(), accommodations);
    }

    @GetMapping
    public ResponseDto<AccommodationDetailGetResponse> searchAccommodations(
            @RequestParam String keyword,
            @RequestParam(value = "area-code") AreaCode areaCode
    ) {
        AccommodationDetailGetResponse accommodationDetailGetResponse =
                accommodationService.bringAccommodationFromAPI(keyword, areaCode);
        return new ResponseDto<>(HttpStatus.OK.value(), accommodationDetailGetResponse);
    }

}
