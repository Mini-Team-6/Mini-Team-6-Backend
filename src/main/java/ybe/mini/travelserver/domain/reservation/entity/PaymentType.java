package ybe.mini.travelserver.domain.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {

    KAKAO_PAY("카카오페이 결제"),
    CARD("카드 결제"),
    MOBILE("휴대폰 결제"),
    ACCOUNT("계좌 이체")
    ;

    private final String name;

}
