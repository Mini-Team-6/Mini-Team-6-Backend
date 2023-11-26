package ybe.mini.travelserver.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateResponse;
import ybe.mini.travelserver.domain.reservation.dto.ReservationGetResponse;
import ybe.mini.travelserver.domain.reservation.service.ReservationService;
import ybe.mini.travelserver.global.common.ResponseDto;
import ybe.mini.travelserver.global.security.PrincipalDetails;

import java.util.List;

@Slf4j
@RequestMapping("/temp/reservations")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseDto<ReservationCreateResponse> tryReservation (
            @RequestBody ReservationCreateRequest createRequest, //To-do : Validation ++
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                reservationService.createReservation(principalDetails.getEmail(), createRequest)
        );         //@Return : status, <생성된 ReservationId>
    }

    @GetMapping
    public ResponseDto<List<ReservationGetResponse>> getMyReservations (
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                reservationService.getMyReservations(principalDetails.getMemberId())
        );
    }

    @PostMapping("/{reservationId}/payment")
    public ResponseDto<Long> payReservation(
            @PathVariable Long reservationId
    ) {
        return new ResponseDto<>(HttpStatus.OK.value(),
                reservationService.updateReservationStatusToPay(reservationId)
        );
    }


    @DeleteMapping("/{reservationId}")
    public ResponseDto<Long> deleteReservation(
            @PathVariable Long reservationId
    ) {
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                reservationService.deleteReservation(reservationId)
        );
    }

}
