package ybe.mini.travelserver.domain.member.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ybe.mini.travelserver.global.exception.ProblemDetailCreator;

import static ybe.mini.travelserver.domain.member.exception.MemberErrorMessage.*;

@RestControllerAdvice
public class MemberExceptionHandler extends ProblemDetailCreator<MemberErrorMessage> {
    protected MemberExceptionHandler() {
        super("회원 처리 실패");
    }

    @ExceptionHandler(MemberAlreadyExistException.class)
    public ProblemDetail handleMemberAlreadyExistException(HttpServletRequest request) {
        return createProblemDetail(MEMBER_ALREADY_EXIST, request);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ProblemDetail handleMemberNotFoundException(HttpServletRequest request) {
        return createProblemDetail(MEMBER_NOT_FOUND, request);
    }

    @ExceptionHandler(CanNotControlOtherMembersData.class)
    public ProblemDetail handleCanNotUpdateWithOtherValidTokens(HttpServletRequest request) {
        return createProblemDetail(CAN_NOT_CONTROL_OTHER_MEMBERS_DATA, request);
    }

}