package ybe.mini.travelserver.global.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtToPrincipalConverter {
    public PrincipalDetails convert(DecodedJWT jwt) {
        return PrincipalDetails.builder()
                .memberId(Long.parseLong(jwt.getSubject()))
                .email(jwt.getClaim("email").asString())
                .build();
    }
}
