package ybe.mini.travelserver.domain.member.dummy;

import ybe.mini.travelserver.global.security.PrincipalDetails;

public interface DummyPrincipal extends DummyMember {

    default PrincipalDetails dummyPrincipalDetails() {
        return PrincipalDetails.builder()
                .memberId(memberId)
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
