package ybe.mini.travelserver.domain.accommodation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.service.AccommodationService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp/accommodations")
@Slf4j
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("/keyword")
    public ResponseDto<List<AccommodationGetResponse>> bringAccommodations(
            @RequestParam String keyword
    ) {
        List<AccommodationGetResponse> accommodations =
                accommodationService.bringAccommodations(keyword);
        return new ResponseDto<>(HttpStatus.OK.value(), accommodations);
    }

    @GetMapping("/areacode")
    public ResponseDto<List<AccommodationGetResponse>> bringAccommodationsByCity(
            @RequestParam String areacode
    ) {
        log.info("areacode: {}", areacode);
//        String areaCode = String.valueOf(areacode);
//        log.info("areaCode: {}", areaCode);
        List<AccommodationGetResponse> accommodations =
                accommodationService.bringAccommodationsByAreaCode(areacode);
        return new ResponseDto<>(HttpStatus.OK.value(), accommodations);
    }


}
