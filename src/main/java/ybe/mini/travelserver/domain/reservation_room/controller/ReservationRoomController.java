package ybe.mini.travelserver.domain.reservation_room.controller;//package ybe.mini.travelserver.domain.reservation_room.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomGetResponse;
import ybe.mini.travelserver.domain.reservation_room.service.ReservationRoomService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.List;

@Slf4j
@RequestMapping("/reservation-rooms")
@RestController
@RequiredArgsConstructor
public class ReservationRoomController {

    private final ReservationRoomService reservationRoomService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{reservationId}")
    public ResponseDto<List<ReservationRoomGetResponse>> getReservationRoomsByReserveId(
            @PathVariable Long reservationId
    ) {

        return new ResponseDto<>(
                HttpStatus.OK.value(),
                reservationRoomService.getReservationRoomsFromReservation(reservationId)
        );
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping
    public ResponseDto<Long> deleteReservationRoom(
            @RequestParam Long reservationId,
            @RequestParam Long reservationRoomId
    ) {
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                reservationRoomService.deleteReservationRoom(reservationId, reservationRoomId)
        );
    }

}
