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
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
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


    @Override
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

    @Override
    @Transactional(readOnly = true)
    public MypageResponse getMemberProfile(PrincipalDetails principalDetails) {
        String email = principalDetails.getUsername();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(NullPointerException::new);

        return MypageResponse.fromEntity(member);
    }

    @Override
    @Transactional
    public MypageUpdateResponse updateMemberProfile(MypageUpdateRequest mypageUpdateRequest) {
        Member member = memberRepository.save(mypageUpdateRequest.toEntity());

        return MypageUpdateResponse.fromEntity(member);
    }

    @Override
    @Transactional
    public DeleteResponse deleteMemberProfile(
            PrincipalDetails principalDetails,
            DeleteRequest deleteRequest
    ) {
        Member member = memberRepository.findByEmailAndPassword(
                deleteRequest.email(),
                passwordEncoder.encode(deleteRequest.password())
        ).orElseThrow(NullPointerException::new);

        memberRepository.delete(member);

        return DeleteResponse.fromEntity(member);
    }
}
