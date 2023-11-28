package ybe.mini.travelserver.global.exception.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

import static ybe.mini.travelserver.global.exception.api.TourAPIErrorMessage.*;


@RestControllerAdvice
public class TourAPIExceptionHandler extends ProblemDetailCreator<TourAPIErrorMessage> {
    protected TourAPIExceptionHandler() {
        super("TourAPI 처리 실패");
    }

    @ExceptionHandler(NoAccommodationsFromAPIException.class)
    public ProblemDetail handleNotGatheredAccommodationsFromAPIException(HttpServletRequest request) {
        return createProblemDetail(NO_ACCOMMODATIONS_FROM_API, request);
    }

    @ExceptionHandler(NoRoomsFromAPIException.class)
    public ProblemDetail handleNotGatheredRoomsFromAPIException(HttpServletRequest request) {
        return createProblemDetail(NO_ROOMS_FROM_API, request);
    }

    @ExceptionHandler(WrongXMLFormatException.class)
    public ProblemDetail handleWrongXMLFormatException(
            WrongXMLFormatException ex,
            HttpServletRequest request) {
        return createProblemDetail(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request);
    }

    @ExceptionHandler(WrongRequestException.class)
    public ProblemDetail handleWrongCallBackException(WrongRequestException ex, HttpServletRequest request) {
        return createProblemDetail(valueOf(ex.getMessage()), request);
    }
}