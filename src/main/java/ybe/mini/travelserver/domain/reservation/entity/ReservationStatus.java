package ybe.mini.travelserver.domain.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {

    PAYED_BEFORE("결제 전"),
    PAYED_SUCCESS("결제 완료")
    ;

    private final String name;

}
