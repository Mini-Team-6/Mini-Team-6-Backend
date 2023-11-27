package ybe.mini.travelserver.global.exception.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

import java.util.Objects;

import static ybe.mini.travelserver.global.exception.dto.DTOErrorMessage.METHOD_ARGUMENT_NOT_VALID;

@RestControllerAdvice
public class DTOExceptionHandler extends ProblemDetailCreator<DTOErrorMessage> {
    protected DTOExceptionHandler() {
        super("DTO 바인딩 실패");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return createProblemDetail(errorMessage, METHOD_ARGUMENT_NOT_VALID.getStatus().value(), request);
    }

}