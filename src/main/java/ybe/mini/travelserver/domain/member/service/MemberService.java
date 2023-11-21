package ybe.mini.travelserver.domain.member.service;

import ybe.mini.travelserver.domain.member.dto.*;
import ybe.mini.travelserver.global.security.PrincipalDetails;

public interface MemberService {

    SigninResponse loginMember(SigninRequest signinRequest);

    SignupResponse createMember(SignupRequest signupRequest);

    MypageResponse getMemberProfile(PrincipalDetails principalDetails);

    MypageUpdateResponse updateMemberProfile(MypageUpdateRequest mypageUpdateRequest);

    DeleteResponse deleteMemberProfile(PrincipalDetails principalDetails, DeleteRequest deleteRequest);
}
