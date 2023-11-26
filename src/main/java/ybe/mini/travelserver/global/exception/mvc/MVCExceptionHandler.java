package ybe.mini.travelserver.global.exception.mvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

import static ybe.mini.travelserver.global.exception.mvc.MVCErrorMessage.NO_HANDLER_FOUND;

@RestControllerAdvice
public class MVCExceptionHandler extends ProblemDetailCreator<MVCErrorMessage> {
    protected MVCExceptionHandler() {
        super("페이지 처리 오류");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ProblemDetail notFoundException(HttpServletRequest request) {
        return createProblemDetail(NO_HANDLER_FOUND, request);
    }
}
