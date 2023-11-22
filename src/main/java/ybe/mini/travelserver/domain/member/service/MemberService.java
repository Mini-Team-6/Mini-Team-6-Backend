package ybe.mini.travelserver.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ybe.mini.travelserver.domain.member.dto.*;
import ybe.mini.travelserver.domain.member.entity.Member;
import ybe.mini.travelserver.domain.member.repository.MemberRepository;
import ybe.mini.travelserver.global.security.JwtIssuer;
import ybe.mini.travelserver.global.security.PrincipalDetails;

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
                new UsernamePasswordAuthenticationToken(signinRequest.email(), signinRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        final String token = jwtIssuer.issue(JwtIssuer.Request.builder()
                .memberId(principal.getMemberId())
                .email(principal.getEmail())
                .build());

        return new SigninResponse(token);
    }

    @Transactional
    public SignupResponse createMember(SignupRequest signupRequest) {
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
                .orElseThrow(NullPointerException::new);

        return MypageResponse.fromEntity(member);
    }


    @Transactional
    public MypageUpdateResponse updateMemberProfile(MypageUpdateRequest mypageUpdateRequest) {
        Member existingMember = memberRepository.findByEmail(mypageUpdateRequest.email())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        return MypageUpdateResponse.fromEntity(
                existingMember.updateProfile(
                        mypageUpdateRequest.email(),
                        passwordEncoder.encode(mypageUpdateRequest.password()),
                        mypageUpdateRequest.name()
                )
        );
    }


    @Transactional
    public boolean deleteMemberProfile(
            PrincipalDetails principalDetails,
            MypageDeleteRequest mypageDeleteRequest
    ) {
        if (!principalDetails.getUsername().equals(mypageDeleteRequest.email())) {
            throw new IllegalArgumentException("Member not found");
        }

        Member existingMember = memberRepository.findByEmail(mypageDeleteRequest.email())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        memberRepository.delete(existingMember);

        return true;
    }
}
