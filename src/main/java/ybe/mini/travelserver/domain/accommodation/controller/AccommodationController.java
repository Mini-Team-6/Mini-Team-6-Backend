package ybe.mini.travelserver.domain.accommodation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationAndRoomResponse;
import ybe.mini.travelserver.domain.accommodation.dto.AccommodationGetResponse;
import ybe.mini.travelserver.domain.accommodation.service.AccommodationService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping
    public ResponseDto<List<AccommodationGetResponse>> searchAccommodations(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, value = "area-code") String areaCode
    ) {
        List<AccommodationGetResponse> accommodations =
                accommodationService.bringAccommodations(keyword, areaCode);
        return new ResponseDto<>(HttpStatus.OK.value(), accommodations);
    }

    @GetMapping("/{accommodationId}")
    public ResponseDto<AccommodationAndRoomResponse> getAccommodationAndRooms(
            @PathVariable Long accommodationId
    ) {
        AccommodationAndRoomResponse accommodationAndRoomResponse
                = accommodationService.bringAccommodationAndRooms(accommodationId);
        return new ResponseDto<>(HttpStatus.OK.value(), accommodationAndRoomResponse);
    }

}
