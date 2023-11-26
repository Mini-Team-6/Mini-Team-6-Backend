package ybe.mini.travelserver.domain.member.dummy;

import ybe.mini.travelserver.domain.member.dto.*;
import ybe.mini.travelserver.domain.member.entity.Member;

public interface DummyMemberDTO extends DummyMember {
    String token = "token";

    default SigninRequest dummySigninRequest() {
        Member member = dummyMember();
        return new SigninRequest(
                member.getEmail(),
                member.getPassword()
        );
    }

    default SigninResponse dummySigninResponse() {
        return new SigninResponse(
                token
        );
    }

    default SignupRequest dummySignupRequest() {
        Member member = dummyMember();
        return new SignupRequest(
                member.getEmail(),
                member.getPassword(),
                member.getName()
        );
    }

    default SignupResponse dummySignupResponse() {
        Member member = dummyMember();
        return new SignupResponse(
                member.getEmail(),
                member.getPassword(),
                member.getName()
        );
    }

    default MypageResponse dummyMypageResponse() {
        Member member = dummyMember();
        return new MypageResponse(
                member.getEmail(),
                member.getName()
        );
    }

    default MypageUpdateRequest dummyMypageUpdateRequest() {
        Member member2 = dummyMemberUpdatedProfile();
        return new MypageUpdateRequest(
                member2.getEmail(),
                member2.getPassword(),
                member2.getName()
        );
    }

    default MypageUpdateResponse dummyMypageUpdateResponse() {
        Member member2 = dummyMemberUpdatedProfile();
        return new MypageUpdateResponse(
                member2.getEmail(),
                bcrptyedNewPassword,
                member2.getName()
        );
    }

    default MypageDeleteRequest dummyMypageDeleteRequest() {
        Member member = dummyMember();
        return new MypageDeleteRequest(
                member.getEmail(),
                member.getPassword()
        );
    }

    default MypageDeleteResponse dummyMypageDeleteResponse() {
        Member member = dummyMember();
        return new MypageDeleteResponse(
                member.getEmail(),
                member.getPassword()
        );
    }
}
