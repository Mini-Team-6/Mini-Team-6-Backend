package ybe.mini.travelserver.domain.reservation_room.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomGetResponse;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus.RESERVED;

@Slf4j
@RequestMapping("/reservation-rooms")
@RestController
@RequiredArgsConstructor
public class ReservationRoomController {

    @GetMapping("/{reservationId}")
    public ResponseDto<List<ReservationRoomGetResponse>> getReservationRoomsByReserveId(
            @PathVariable Long reservationId,
            Principal principal
    ) {
        ReservationRoomGetResponse reservationRoomDto1 =
                ReservationRoomGetResponse.builder()
                        .id(1L)
                        .roomId(13223L)
                        .checkIn(LocalDateTime.of(2023, 1,1,15,0))
                        .checkOut(LocalDateTime.of(2023, 1,5,12,0))
                        .guestNumber(2L)
                        .status(RESERVED)
                        .build();
        ReservationRoomGetResponse reservationRoomDto2 =
                ReservationRoomGetResponse.builder()
                        .id(2L)
                        .roomId(23333L)
                        .checkIn(LocalDateTime.of(2023, 2,1,15,0))
                        .checkOut(LocalDateTime.of(2023, 2,5,12,0))
                        .guestNumber(2L)
                        .status(RESERVED)
                        .build();
        return new ResponseDto<>(HttpStatus.OK.value(), List.of(reservationRoomDto1, reservationRoomDto2));
    }

    @DeleteMapping("/{reservationRoomId}")
    public ResponseDto<Integer> deleteReservationRoom(
            @PathVariable Long reservationRoomId,
            Principal principal
    ) {
        return new ResponseDto<>(HttpStatus.OK.value(), 1);         //@Return : status, <생성된 ReservationId>
    }

}
