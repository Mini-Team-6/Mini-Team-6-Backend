package ybe.mini.travelserver.domain.reservation_room.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

import static ybe.mini.travelserver.domain.reservation_room.exception.ReservationRoomErrorMessage.RESERVATION_ROOM_NOT_FOUND;

@RestControllerAdvice
public class ReservationRoomExceptionHandler extends ProblemDetailCreator<ReservationRoomErrorMessage> {

    protected ReservationRoomExceptionHandler() {
        super("예약 객실 처리 실패");
    }

    @ExceptionHandler(ReservationRoomNotFoundException.class)
    public ProblemDetail handleReservationRoomNotFoundException(HttpServletRequest request) {
        return createProblemDetail(RESERVATION_ROOM_NOT_FOUND, request);
    }
}
