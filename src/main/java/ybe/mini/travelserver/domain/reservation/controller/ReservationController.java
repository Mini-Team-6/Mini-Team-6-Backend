package ybe.mini.travelserver.domain.reservation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateResponse;
import ybe.mini.travelserver.domain.reservation.dto.ReservationGetResponse;
import ybe.mini.travelserver.domain.reservation.service.ReservationService;
import ybe.mini.travelserver.global.common.ResponseDto;
import ybe.mini.travelserver.global.security.PrincipalDetails;

import java.util.List;

import static ybe.mini.travelserver.global.security.Role.ROLE_USER;

@Slf4j
@RequestMapping("/temp/reservations")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseDto<ReservationCreateResponse> tryReservation (
            @RequestBody @Valid ReservationCreateRequest createRequest, //Todo : Validation ++
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        return new ResponseDto<>(
                HttpStatus.CREATED.value(),
                reservationService.createReservation(principalDetails.getEmail(), createRequest)
        );
    }

    @PreAuthorize("hasRole('ROLE_USER')")
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
