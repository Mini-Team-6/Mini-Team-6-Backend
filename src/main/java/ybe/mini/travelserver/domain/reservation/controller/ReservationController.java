package ybe.mini.travelserver.domain.reservation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateFromCartRequest;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateResponse;
import ybe.mini.travelserver.domain.reservation.dto.ReservationGetResponse;
import ybe.mini.travelserver.domain.reservation.service.ReservationService;
import ybe.mini.travelserver.global.common.ResponseDto;
import ybe.mini.travelserver.global.security.PrincipalDetails;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static ybe.mini.travelserver.global.security.Role.HAS_ROLE_USER;

@Slf4j
@RequestMapping("/reservations")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PreAuthorize(HAS_ROLE_USER)
    @PostMapping
    public ResponseDto<ReservationCreateResponse> tryReservation(
            @RequestBody ReservationCreateRequest createRequest,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        return new ResponseDto<>(
                HttpStatus.CREATED.value(),
                reservationService.createReservation(principalDetails.getEmail(), createRequest)
        );
    }

    @PreAuthorize(HAS_ROLE_USER)
    @PostMapping("/from-cart")
    public ResponseDto<ReservationCreateResponse> tryReservationFromCart(
            @RequestBody @Valid ReservationCreateFromCartRequest createRequest,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        return new ResponseDto<>(
                HttpStatus.CREATED.value(),
                reservationService.createReservationAndDeleteCart(principalDetails.getEmail(), createRequest)
        );
    }

    @PreAuthorize(HAS_ROLE_USER)
    @GetMapping
    public ResponseDto<List<ReservationGetResponse>> getMyReservations(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PageableDefault(size = 3, sort = "id", direction = DESC) Pageable pageable
    ) {

        return new ResponseDto<>(
                HttpStatus.OK.value(),
                reservationService.getMyReservations(principalDetails.getMemberId(), pageable)
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
