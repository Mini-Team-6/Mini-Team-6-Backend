package ybe.mini.travelserver.domain.reservation.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

import static ybe.mini.travelserver.domain.reservation.exception.ReservationErrorMessage.RESERVATION_NOT_FOUND;
import static ybe.mini.travelserver.domain.reservation.exception.ReservationErrorMessage.ROOM_STOCK_IS_EMPTY;

@RestControllerAdvice
public class ReservationExceptionHandler extends ProblemDetailCreator<ReservationErrorMessage> {

    protected ReservationExceptionHandler() {
        super("예약 처리 실패");
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ProblemDetail handleReservationNotFoundException(HttpServletRequest request) {
        return createProblemDetail(RESERVATION_NOT_FOUND, request);
    }

    @ExceptionHandler(RoomStockIsEmptyException.class)
    public ProblemDetail handleRoomStockIsEmptyException(HttpServletRequest request) {
        return createProblemDetail(ROOM_STOCK_IS_EMPTY, request);
    }


}
