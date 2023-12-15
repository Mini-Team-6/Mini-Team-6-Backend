package ybe.mini.travelserver.domain.member.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
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
import ybe.mini.travelserver.global.security.JwtService;
import ybe.mini.travelserver.global.security.PrincipalDetails;

import java.util.Collections;
import java.util.UUID;

import static ybe.mini.travelserver.global.security.Role.ROLE_USER;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
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

        String uuid = UUID.randomUUID().toString();

        String email = principal.getEmail();
        final String accessToken = jwtService.createAccessToken(email, uuid);
        final String refreshToken = jwtService.createRefreshToken(email, uuid);

        jwtService.saveRefreshToken(uuid, refreshToken);

        return new SigninResponse(accessToken);
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

        String newPassword = mypageUpdateRequest.password();
        String newName = mypageUpdateRequest.name();

        return MypageUpdateResponse.fromEntity(
                existingMember.updateProfile(
                        principalDetails.getEmail(),
                        newPassword == null ? principalDetails.getPassword() : passwordEncoder.encode(newPassword),
                        newName == null ? principalDetails.getName() : mypageUpdateRequest.name()
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

    public void logoutMember(HttpServletRequest request) {
        String extractedToken = jwtService.extractTokenFromRequest(request);
        DecodedJWT decodedJWT = jwtService.decode(extractedToken);

        jwtService.deleteRefreshToken(decodedJWT.getId());
    }


}
