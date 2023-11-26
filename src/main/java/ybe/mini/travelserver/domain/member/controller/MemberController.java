package ybe.mini.travelserver.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.member.dto.*;
import ybe.mini.travelserver.domain.member.service.MemberService;
import ybe.mini.travelserver.global.common.ResponseDto;
import ybe.mini.travelserver.global.security.PrincipalDetails;

import static ybe.mini.travelserver.global.security.Role.ROLE_USER;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signin")
    public ResponseDto<SigninResponse> signin(@RequestBody @Valid SigninRequest signinRequest) {
        SigninResponse signinResponse = memberService.loginMember(signinRequest);

        return new ResponseDto<>(HttpStatus.OK.value(), signinResponse);
    }

    @PostMapping("/signup")
    public ResponseDto<SignupResponse> signup(@RequestBody @Valid SignupRequest signupRequest) {
        SignupResponse signupResponse = memberService.createMember(signupRequest);

        return new ResponseDto<>(HttpStatus.OK.value(), signupResponse);
    }

    @PreAuthorize(ROLE_USER)
    @GetMapping("/mypage")
    public ResponseDto<MypageResponse> bringMember(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        MypageResponse mypageResponse = memberService.getMemberProfile(principalDetails);

        return new ResponseDto<>(HttpStatus.OK.value(), mypageResponse);
    }

    @PreAuthorize(ROLE_USER)
    @PatchMapping("/mypage")
    public ResponseDto<MypageUpdateResponse> updateMember(@RequestBody @Valid MypageUpdateRequest mypageUpdateRequest) {
        MypageUpdateResponse mypageUpdateResponse = memberService.updateMemberProfile(mypageUpdateRequest);

        return new ResponseDto<>(HttpStatus.OK.value(), mypageUpdateResponse);
    }

    @PreAuthorize(ROLE_USER)
    @DeleteMapping("/mypage")
    public ResponseDto<MypageDeleteResponse> deleteMember(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid MypageDeleteRequest mypageDeleteRequest
    ) {
        MypageDeleteResponse mypageDeleteResponse = memberService.deleteMemberProfile(
                principalDetails,
                mypageDeleteRequest
        );

        return new ResponseDto<>(HttpStatus.OK.value(), mypageDeleteResponse);
    }
}
