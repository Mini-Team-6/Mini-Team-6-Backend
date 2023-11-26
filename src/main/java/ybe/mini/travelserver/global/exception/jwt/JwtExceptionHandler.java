package ybe.mini.travelserver.global.exception.jwt;

import com.auth0.jwt.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

import static ybe.mini.travelserver.global.exception.jwt.JwtErrorMessage.*;

@RestControllerAdvice
public class JwtExceptionHandler extends ProblemDetailCreator<JwtErrorMessage> {
    public JwtExceptionHandler() {
        super("JWT 처리 실패");
    }

    @ExceptionHandler(AlgorithmMismatchException.class)
    public ProblemDetail handleAlgorithmMismatchException(HttpServletRequest request) {
        return createProblemDetail(ALGORITHM_MISMATCH, request);
    }

    @ExceptionHandler(InvalidClaimException.class)
    public ProblemDetail handleInvalidClaimException(HttpServletRequest request) {
        return createProblemDetail(INVALID_CLAIM, request);
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ProblemDetail handleJWTDecodeException(HttpServletRequest request) {
        return createProblemDetail(JWT_DECODE, request);
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ProblemDetail handleSignatureVerificationException(HttpServletRequest request) {
        return createProblemDetail(SIGNATURE_VERIFICATION, request);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ProblemDetail handleTokenExpiredException(HttpServletRequest request) {
        return createProblemDetail(TOKEN_EXPIRED, request);
    }

    @ExceptionHandler(SignatureGenerationException.class)
    public ProblemDetail handleSignatureGenerationException(HttpServletRequest request) {
        return createProblemDetail(SIGNATURE_GENERATION, request);
    }
}