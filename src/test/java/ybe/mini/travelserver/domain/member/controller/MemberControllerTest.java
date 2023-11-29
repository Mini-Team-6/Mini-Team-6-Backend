package ybe.mini.travelserver.domain.member.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ybe.mini.travelserver.domain.member.dummy.DummyMemberDTO;
import ybe.mini.travelserver.domain.member.dummy.DummyPrincipal;
import ybe.mini.travelserver.domain.member.service.MemberService;
import ybe.mini.travelserver.global.common.ResponseDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest implements DummyMemberDTO, DummyPrincipal {
    @Mock
    MemberService memberService;
    @InjectMocks
    MemberController memberController;

    @Test
    @DisplayName("로그인 테스트")
    void testSignin() {
        // given
        given(memberService.loginMember(any())).willReturn(dummySigninResponse());

        // when
        var actual = memberController.signin(dummySigninRequest());

        // then
        var expected = new ResponseDto<>(200, dummySigninResponse());
        assertEquals(expected, actual);
        then(memberService).should().loginMember(any());
    }

    @Test
    @DisplayName("회원가입 테스트")
    void testSignup() {
        // given
        given(memberService.createMember(any())).willReturn(dummySignupResponse());

        // when
        var actual = memberController.signup(dummySignupRequest());

        // then
        var expected = new ResponseDto<>(200, dummySignupResponse());
        assertEquals(expected, actual);
        then(memberService).should().createMember(any());
    }

    @Test
    @DisplayName("회원정보 조회 테스트")
    void testBringMember() {
        // given
        given(memberService.getMemberProfile(any())).willReturn(dummyMypageResponse());

        // when
        var actual = memberController.bringMember(dummyPrincipalDetails());

        // then
        var expected = new ResponseDto<>(200, dummyMypageResponse());
        assertEquals(expected, actual);
        then(memberService).should().getMemberProfile(any());
    }

    @Test
    @DisplayName("회원정보 수정 테스트")
    void testUpdateMember() {
        // given
        given(memberService.updateMemberProfile(any(),any())).willReturn(dummyMypageUpdateResponse());

        // when
        var actual = memberController.updateMember(
                dummyPrincipalDetails(),
                dummyMypageUpdateRequest()
        );

        // then
        var expected = new ResponseDto<>(200, dummyMypageUpdateResponse());
        assertEquals(expected, actual);
        then(memberService).should().updateMemberProfile(any(), any());
    }

    @Test
    @DisplayName("회원정보 삭제 테스트")
    void testDeleteMember() {
        // given
        given(memberService.deleteMemberProfile(any(), any())).willReturn(dummyMypageDeleteResponse());

        // when
        var actual = memberController.deleteMember(
                dummyPrincipalDetails(),
                dummyMypageDeleteRequest()
        );

        // then
        var expected = new ResponseDto<>(200, dummyMypageDeleteResponse());
        assertEquals(expected, actual);
        then(memberService).should().deleteMemberProfile(any(), any());
    }
}