package ybe.mini.travelserver.global.exception.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

import static ybe.mini.travelserver.global.exception.security.SecurityErrorMessage.*;

@RestControllerAdvice
public class SecurityExceptionHandler extends ProblemDetailCreator<SecurityErrorMessage> {
    protected SecurityExceptionHandler() {
        super("인증, 인가 처리 실패");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(HttpServletRequest request) {
        return createProblemDetail(AUTHENTICATION_FAILED, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(HttpServletRequest request) {
        return createProblemDetail(ACCESS_DENIED, request);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ProblemDetail handleAuthenticationServiceException(HttpServletRequest request) {
        return createProblemDetail(UN_AUTHORIZED, request);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ProblemDetail handleUsernameNotFoundException(HttpServletRequest request) {
        return createProblemDetail(USERNAME_NOT_FOUND, request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(HttpServletRequest request) {
        return createProblemDetail(BAD_CREDENTIALS, request);
    }

    @ExceptionHandler(AccountExpiredException.class)
    public ProblemDetail handleAccountExpiredException(HttpServletRequest request) {
        return createProblemDetail(ACCOUNT_EXPIRED, request);
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ProblemDetail handleCredentialsExpiredException(HttpServletRequest request) {
        return createProblemDetail(CREDENTIALS_EXPIRED, request);
    }

    @ExceptionHandler(DisabledException.class)
    public ProblemDetail handleDisabledException(HttpServletRequest request) {
        return createProblemDetail(DISABLED, request);
    }

    @ExceptionHandler(LockedException.class)
    public ProblemDetail handleLockedException(HttpServletRequest request) {
        return createProblemDetail(LOCKED, request);
    }
}