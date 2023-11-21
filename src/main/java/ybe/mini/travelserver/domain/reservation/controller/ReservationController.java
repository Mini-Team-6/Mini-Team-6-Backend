package ybe.mini.travelserver.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.dto.ReservationGetResponse;
import ybe.mini.travelserver.domain.reservation.entity.ReservationStatus;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomCreateRequest;
import ybe.mini.travelserver.domain.reservation_room.dto.ReservationRoomGetResponse;
import ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static ybe.mini.travelserver.domain.reservation.entity.ReservationStatus.PAYED_BEFORE;
import static ybe.mini.travelserver.domain.reservation.entity.ReservationStatus.PAYED_SUCCESS;
import static ybe.mini.travelserver.domain.reservation_room.entity.ReservationRoomStatus.RESERVED;

@Slf4j
@RequestMapping("/reservations")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    @PostMapping
    public ResponseDto<Integer> tryReservation (
            @RequestBody ReservationCreateRequest createRequest, //To-do : Validation ++
            Principal principal
    ) {
        //To-do : Service에서 Reservation, List<ReservationRoom> 생성 후 매핑
        return new ResponseDto<>(HttpStatus.OK.value(), 1);         //@Return : status, <생성된 ReservationId>
    }

    @PostMapping("/pay/{reservationId}")
    public ResponseDto<Integer> payReservation(
            @PathVariable Long reservationId,
            Principal principal
    ) {
        //To-do : Service에서 Reservation, List<ReservationRoom> 생성 후 매핑
        return new ResponseDto<>(HttpStatus.OK.value(), 1);         //@Return : status, <생성된 ReservationId>
    }

    @GetMapping("/myAll")
    public ResponseDto<List<ReservationGetResponse>> getMyReservations(Principal principal) {

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
        ReservationGetResponse reservationGetResponse1 =
                ReservationGetResponse.builder()
                        .id(1L)
                        .reservationRooms(List.of(reservationRoomDto1, reservationRoomDto2))
                        .createdAt(LocalDateTime.of(2023, 1,1,12,0))
                        .status(PAYED_BEFORE)
                        .build();
        ReservationGetResponse reservationGetResponse2 =
                ReservationGetResponse.builder()
                        .id(1L)
                        .reservationRooms(List.of(reservationRoomDto1, reservationRoomDto2))
                        .createdAt(LocalDateTime.of(2023, 1,1,12,0))
                        .status(PAYED_SUCCESS)
                        .build();
        return new ResponseDto<>(HttpStatus.OK.value(), List.of(reservationGetResponse1, reservationGetResponse2));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseDto<Integer> deleteReservation(
            @PathVariable Long reservationId,
            Principal principal
    ) {
        return new ResponseDto<>(HttpStatus.OK.value(), 1);         //@Return : status, <생성된 ReservationId>
    }

}
