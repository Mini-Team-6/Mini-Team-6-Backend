package ybe.mini.travelserver.domain.member.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ybe.mini.travelserver.domain.member.dummy.DummyMember;
import ybe.mini.travelserver.domain.member.entity.Member;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class MemberRepositoryTest implements DummyMember {

    @Mock
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 단건 조회 - 존재하는 경우")
    void testGetMember_success() {
        // given
        given(memberRepository.findByEmail(email)).willReturn(Optional.of(dummyMember()));

        // when
        Optional<Member> actual = memberRepository.findByEmail(email);

        // then
        assertTrue(actual.isPresent());
        then(memberRepository).should().findByEmail(email);
    }

    @Test
    @DisplayName("회원 단건 조회 - 존재하지 않는 경우")
    void testGetMember_notFound() {
        // given
        given(memberRepository.findByEmail(wrongEmail)).willReturn(Optional.empty());

        // when
        Optional<Member> actual = memberRepository.findByEmail(email);

        // then
        assertTrue(actual.isEmpty());
        then(memberRepository).should().findByEmail(email);
    }
}