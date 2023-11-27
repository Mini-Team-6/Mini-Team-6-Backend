package ybe.mini.travelserver.domain.reservation.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ybe.mini.travelserver.domain.reservation.controller.ReservationController;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

import static ybe.mini.travelserver.domain.reservation.exception.ReservationErrorMessage.HTTP_MESSAGE_NOT_READABLE;
import static ybe.mini.travelserver.domain.reservation.exception.ReservationErrorMessage.RESERVATION_NOT_FOUND;

@RestControllerAdvice(basePackageClasses = ReservationController.class)
public class ReservationExceptionHandler extends ProblemDetailCreator<ReservationErrorMessage> {

    protected ReservationExceptionHandler() {
        super("예약 처리 실패");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(HttpServletRequest request) {
        return createProblemDetail(HTTP_MESSAGE_NOT_READABLE, request); //todo : Global로 분리할지 논의
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ProblemDetail handleReservationNotFoundException(HttpServletRequest request) {
        return createProblemDetail(RESERVATION_NOT_FOUND, request);
    }


}
