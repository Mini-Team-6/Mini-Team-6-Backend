package ybe.mini.travelserver.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ybe.mini.travelserver.global.exception.ErrorMessage;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum MemberErrorMessage implements ErrorMessage {
    MEMBER_ALREADY_EXIST(BAD_REQUEST, "회원이 이미 있습니다"),
    MEMBER_NOT_FOUND(BAD_REQUEST, "없는 회원입니다");
    private final HttpStatus status;
    private final String message;
}
