package ybe.mini.travelserver.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ybe.mini.travelserver.domain.member.dto.*;
import ybe.mini.travelserver.domain.member.service.MemberService;
import ybe.mini.travelserver.global.security.PrincipalDetails;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid SigninRequest signinRequest) {
        SigninResponse signinResponse = memberService.loginMember(signinRequest);
        return ResponseEntity.ok(signinResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest signupRequest) {
        SignupResponse signupResponse = memberService.createMember(signupRequest);

        URI createdUri = URI.create("/home");
        return ResponseEntity.created(createdUri).body(signupResponse);
    }

    @GetMapping("/mypage")
    public ResponseEntity<?> bringMember(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        MypageResponse mypageResponse = memberService.getMemberProfile(principalDetails);
        return ResponseEntity.ok(mypageResponse);
    }


    @PatchMapping("/mypage")
    public ResponseEntity<?> updateMember(@RequestBody @Valid MypageUpdateRequest mypageUpdateRequest) {
        MypageUpdateResponse mypageUpdateResponse = memberService.updateMemberProfile(mypageUpdateRequest);

        return ResponseEntity.ok(mypageUpdateResponse);
    }


    @DeleteMapping("/mypage")
    public ResponseEntity<?> deleteMember(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid DeleteRequest deleteRequest
    ) {
        memberService.deleteMemberProfile(principalDetails, deleteRequest);

        return ResponseEntity.noContent().build();
    }
}
