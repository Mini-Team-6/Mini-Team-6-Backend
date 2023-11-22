package ybe.mini.travelserver.domain.accommodation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ybe.mini.travelserver.domain.accommodation.entity.AccommodationType;
import ybe.mini.travelserver.domain.accommodation.Location;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.entity.Accommodation;
import ybe.mini.travelserver.domain.accommodation.service.AccommodationService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("/keyword")
    public ResponseDto<List<AccommodationGetResponse>> getAllAccommodationsByKeyword(
            @RequestParam String keyword
    ) {
        List<AccommodationGetResponse> accommodations =
                accommodationService.getAllAccommodationsByKeyword(keyword);
        return new ResponseDto<>(HttpStatus.OK.value(), accommodations);
    }

    @GetMapping("/city")
    public ResponseDto<List<AccommodationGetResponse>> getAllAccommodationsByCityAndDistrict(
            @RequestParam String city,
            @RequestParam String district
    ) {
        return null;
    }


    @GetMapping("/search")
    public ResponseDto<List<AccommodationGetResponse>> searchAccommodation() {
        return null;
    }

}
