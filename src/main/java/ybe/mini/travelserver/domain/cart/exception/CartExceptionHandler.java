package ybe.mini.travelserver.domain.cart.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ybe.mini.travelserver.domain.cart.controller.CartController;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

import static ybe.mini.travelserver.domain.cart.exception.CartErrorMessage.*;

@RestControllerAdvice(basePackageClasses = CartController.class)
public class CartExceptionHandler extends ProblemDetailCreator<CartErrorMessage> {

    protected CartExceptionHandler() {
        super("장바구니 처리 실패");
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ProblemDetail handelCartNotFoundException(HttpServletRequest request) {
        return createProblemDetail(CART_NOT_FOUND, request);
    }

    @ExceptionHandler(CartInvalidMemberException.class)
    public ProblemDetail handelCartInvalidMemberException(HttpServletRequest request) {
        return createProblemDetail(CART_INVALID_MEMBER, request);
    }

    @ExceptionHandler(CartAleadyExistException.class)
    public ProblemDetail handelCartAleadyExistException(HttpServletRequest request) {
        return createProblemDetail(CART_ALREADY_EXIST, request);
    }

}