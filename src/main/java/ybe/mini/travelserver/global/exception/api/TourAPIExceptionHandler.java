package ybe.mini.travelserver.global.exception.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

@RestControllerAdvice
public class TourAPIExceptionHandler extends ProblemDetailCreator<TourAPIErrorMessage> {
    protected TourAPIExceptionHandler() {
        super("TourAPI 처리 실패");
    }

    @ExceptionHandler(NoAccommodationsFromAPIException.class)
    public ProblemDetail handleNotGatheredAccommodationsFromAPIException(HttpServletRequest request) {
        return createProblemDetail(TourAPIErrorMessage.NO_ACCOMMODATIONS_FROM_API, request);
    }

    @ExceptionHandler(NoRoomsFromAPIException.class)
    public ProblemDetail handleNotGatheredRoomsFromAPIException(HttpServletRequest request) {
        return createProblemDetail(TourAPIErrorMessage.NO_ROOMS_FROM_API, request);
    }

}