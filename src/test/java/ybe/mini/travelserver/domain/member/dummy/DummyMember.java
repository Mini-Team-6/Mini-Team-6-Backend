package ybe.mini.travelserver.domain.member.dummy;

import ybe.mini.travelserver.domain.member.entity.Member;

public interface DummyMember {
    Long memberId = 1L;
    String email = "okjaeook98@gmail.com";
    String wrongEmail = "ppap@gmail.com";
    String password = "123456";
    String name = "okjaeook";
    String newPassword = "123";
    String newName = "ojo";
    String bcrptyedNewPassword = "$2a$12$Kr6ZWxFqYCix6Glq53u2a.UWFAvknER1K6XMAqhMYQnYIT2VsA9YS";

    default Member dummyMember() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }

    default Member dummyMemberUpdatedProfile() {
        return Member.builder()
                .email(email)
                .password(newPassword)
                .name(newName)
                .build();
    }
}
