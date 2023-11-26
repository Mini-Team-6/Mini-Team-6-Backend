package ybe.mini.travelserver.domain.reservation.controller;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ybe.mini.travelserver.domain.member.dummy.DummyPrincipal;
import ybe.mini.travelserver.domain.reservation.dto.ReservationCreateRequest;
import ybe.mini.travelserver.domain.reservation.dummy.DummyReservationDTO;
import ybe.mini.travelserver.domain.reservation.service.ReservationService;
import ybe.mini.travelserver.global.common.ResponseDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest implements DummyReservationDTO, DummyPrincipal {

    @Mock
    ReservationService reservationService;

    @InjectMocks
    ReservationController reservationController;

    @Test
    @DisplayName("예약 생성 테스트")
    void tryReservation_success() {
        //given
        given(reservationService.createReservation(any(String.class), any(ReservationCreateRequest.class)))
                .willReturn(dummyReservationCreateRes());

        //when
        var actual = reservationController.tryReservation(
                dummyReservationCreateReq(),
                dummyPrincipalDetails()
        );

        //then
        var expected = new ResponseDto<>(CREATED.value(), dummyReservationCreateRes());
        assertEquals(expected, actual);
        then(reservationService).should().createReservation(any(String.class), any(ReservationCreateRequest.class));
    }

    @Test
    @DisplayName("예약 조회 테스트")
    void getMyReservations_success() {
        //given
        given(reservationService.getMyReservations(any()))
                .willReturn(List.of(dummyReservationGetRes()));

        //when
        var actual = reservationController.getMyReservations(dummyPrincipalDetails());

        //then
        var expected = new ResponseDto<>(OK.value(), List.of(dummyReservationGetRes()));
        Javers javers = JaversBuilder.javers().build();
        assertFalse(javers.compare(actual, expected).hasChanges());
        then(reservationService).should().getMyReservations(any());
    }

    @Test
    @DisplayName("예약 결제")
    void payReservation_success() {
        //given
        given(reservationService.updateReservationStatusToPay(any()))
                .willReturn(1L);

        //when
        var actual = reservationController.payReservation(1L);

        //then
        var expected = new ResponseDto<>(OK.value(), 1L);
        assertEquals(actual, expected);
        then(reservationService).should().updateReservationStatusToPay(any());
    }

    @Test
    @DisplayName("예약 삭제")
    void deleteReservation_success() {
        //given
        given(reservationService.deleteReservation(any()))
                .willReturn(1L);

        //when
        var actual = reservationController.deleteReservation(1L);

        //then
        var expected = new ResponseDto<>(OK.value(), 1L);
        assertEquals(actual, expected);
        then(reservationService).should().deleteReservation(any());
    }

}