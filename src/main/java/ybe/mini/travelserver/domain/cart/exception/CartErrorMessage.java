package ybe.mini.travelserver.domain.cart.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ybe.mini.travelserver.global.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
@Getter
@AllArgsConstructor
public enum CartErrorMessage implements ErrorMessage {

    CART_ALREADY_EXIST(BAD_REQUEST, "해당 정보가 이미 장바구니에 담겨있습니다."),

    CART_NOT_FOUND(BAD_REQUEST, "해당 ID의 장바구니 정보가 없습니다.")
    ;
    private final HttpStatus status;
    private final String message;
}