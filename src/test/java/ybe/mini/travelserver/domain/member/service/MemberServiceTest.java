package ybe.mini.travelserver.domain.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import ybe.mini.travelserver.domain.member.dummy.DummyMemberDTO;
import ybe.mini.travelserver.domain.member.dummy.DummyPrincipal;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.global.security.JwtIssuer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest implements DummyMemberDTO, DummyPrincipal {
    @Mock
    MemberRepository memberRepository;
    @Mock
    JwtIssuer jwtIssuer;
    @Mock
    @SuppressWarnings("unused")
    PasswordEncoder passwordEncoder;
    @Mock
    @SuppressWarnings("unused")
    AuthenticationManager authenticationManager;

    @InjectMocks
    MemberService memberService;

    @Test
    @WithAnonymousUser
    @DisplayName("로그인 테스트")
    void testLoginMember() {
        // given
        Authentication authentication = Mockito.mock(Authentication.class);
        given(authenticationManager.authenticate(any())).willReturn(authentication);
        given(authentication.getPrincipal()).willReturn(dummyPrincipalDetails());
        given(jwtIssuer.issue(any())).willReturn(token);

        // given
        var actual = memberService.loginMember(dummySigninRequest());

        // then
        var expected = dummySigninResponse();
        assertEquals(expected, actual);
        then(authenticationManager).should().authenticate(any());
        then(jwtIssuer).should().issue(any());
    }

    @Test
    @WithAnonymousUser
    @DisplayName("회원가입 테스트")
    void testCreateMember() {
        // given
        given(memberRepository.save(any())).willReturn(dummyMember());

        // when
        var actual = memberService.createMember(dummySignupRequest());

        // then
        var expected = dummySignupResponse();
        assertEquals(expected, actual);
        then(memberRepository).should().save(any());
    }

    @Test
    @WithMockUser
    @DisplayName("회원정보 조회 테스트")
    void testGetMemberProfile() {
        // given
        given(memberRepository.findByEmail(email)).willReturn(Optional.ofNullable(dummyMember()));

        // when
        var actual = memberService.getMemberProfile(dummyPrincipalDetails());

        // then
        var expected = dummyMypageResponse();
        assertEquals(expected, actual);
        then(memberRepository).should().findByEmail(email);
    }

    @Test
    @WithMockUser
    @DisplayName("회원정보 수정 테스트")
    void testUpdateMemberProfile() {
        // given
        given(memberRepository.findByEmail(email)).willReturn(Optional.ofNullable(dummyMember()));
        given(passwordEncoder.encode(newPassword)).willReturn(bcrptyedNewPassword);

        // when
        var actual = memberService.updateMemberProfile(
                dummyPrincipalDetails(),
                dummyMypageUpdateRequest()
        );

        // then
        var expected = dummyMypageUpdateResponse();
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser
    @DisplayName("회원정보 삭제 테스트")
    void testDeleteMemberProfile() {
        // given
        given(memberRepository.findByEmail(email)).willReturn(Optional.ofNullable(dummyMember()));

        // when
        var actual = memberService.deleteMemberProfile(
                dummyPrincipalDetails(),
                dummyMypageDeleteRequest()
        );

        // then
        var expected = dummyMypageDeleteResponse();
        assertEquals(expected, actual);
    }
}
