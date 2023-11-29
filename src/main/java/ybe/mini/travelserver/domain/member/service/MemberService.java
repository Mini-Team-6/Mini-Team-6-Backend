package ybe.mini.travelserver.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.member.dto.*;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.member.exception.CanNotControlOtherMembersData;
import ybe.mini.travelserver.domain.member.exception.MemberAlreadyExistException;
import ybe.mini.travelserver.domain.member.exception.MemberNotFoundException;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.global.security.JwtIssuer;
import ybe.mini.travelserver.global.security.PrincipalDetails;

import java.util.Collections;

import static ybe.mini.travelserver.global.security.Role.ROLE_USER;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    public SigninResponse loginMember(SigninRequest signinRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signinRequest.email(),
                        signinRequest.password(),
                        Collections.singleton(new SimpleGrantedAuthority(ROLE_USER))
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        final String token = jwtIssuer.issue(JwtIssuer.Request.builder()
                .memberId(principal.getMemberId())
                .email(principal.getEmail())
                .name(principal.getName())
                .password(principal.getPassword())
                .build());

        return new SigninResponse(token);
    }

    @Transactional
    public SignupResponse createMember(SignupRequest signupRequest) {
        if (memberRepository.existsByEmail(signupRequest.email())) {
            throw new MemberAlreadyExistException();
        }

        return SignupResponse.fromEntity(memberRepository.save(
                new SignupRequest(
                        signupRequest.email(),
                        passwordEncoder.encode(signupRequest.password()),
                        signupRequest.name()
                ).toEntity()
        ));
    }


    @Transactional(readOnly = true)
    public MypageResponse getMemberProfile(PrincipalDetails principalDetails) {
        String email = principalDetails.getUsername();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);

        return MypageResponse.fromEntity(member);
    }


    @Transactional
    public MypageUpdateResponse updateMemberProfile(
            PrincipalDetails principalDetails,
            MypageUpdateRequest mypageUpdateRequest
    ) {
        Member existingMember = memberRepository.findByEmail(principalDetails.getEmail())
                .orElseThrow(MemberNotFoundException::new);

        return MypageUpdateResponse.fromEntity(
                existingMember.updateProfile(
                        principalDetails.getEmail(),
                        passwordEncoder.encode(mypageUpdateRequest.password()),
                        mypageUpdateRequest.name()
                )
        );
    }


    @Transactional
    public MypageDeleteResponse deleteMemberProfile(
            PrincipalDetails principalDetails,
            MypageDeleteRequest mypageDeleteRequest
    ) {
        if (!principalDetails.getEmail().equals(mypageDeleteRequest.email())) {
            throw new CanNotControlOtherMembersData();
        }

        Member existingMember = memberRepository.findByEmail(principalDetails.getEmail())
                .orElseThrow(MemberNotFoundException::new);

        memberRepository.delete(existingMember);

        return MypageDeleteResponse.fromEntity(existingMember);
    }
}
