package ybe.mini.travelserver.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.member.dto.*;
import ybe.mini.travelserver.domain.member.service.MemberService;
import ybe.mini.travelserver.global.common.ResponseDto;
import ybe.mini.travelserver.global.security.PrincipalDetails;

import java.net.URI;

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

        URI createdUri = URI.create("/home");
        return new ResponseDto<>(HttpStatus.OK.value(), signupResponse);
    }

    @GetMapping("/mypage")
    public ResponseDto<MypageResponse> bringMember(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        MypageResponse mypageResponse = memberService.getMemberProfile(principalDetails);
        return new ResponseDto<>(HttpStatus.OK.value(), mypageResponse);
    }


    @PatchMapping("/mypage")
    public ResponseDto<MypageUpdateResponse> updateMember(@RequestBody @Valid MypageUpdateRequest mypageUpdateRequest) {
        MypageUpdateResponse mypageUpdateResponse = memberService.updateMemberProfile(mypageUpdateRequest);

        return new ResponseDto<>(HttpStatus.OK.value(), mypageUpdateResponse);
    }


    @DeleteMapping("/mypage")
    public ResponseDto<Integer> deleteMember(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid MypageDeleteRequest mypageDeleteRequest
    ) {
        memberService.deleteMemberProfile(mypageDeleteRequest);

        return new ResponseDto<>(HttpStatus.OK.value(), 1); //To do : 1대신 삭제된 ID를 반환해주면 좋을거 같습니다.
    }
}
