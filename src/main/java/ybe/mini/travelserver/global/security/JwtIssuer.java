package ybe.mini.travelserver.global.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class JwtIssuer {
    private final JwtProperties properties;

    public String issue(Request request) {
        var now = Instant.now();

        return JWT.create()
                .withSubject(String.valueOf(request.memberId))
                .withIssuedAt(now)
                .withExpiresAt(now.plus(properties.getTokenDuration()))
                .withClaim("email", request.getEmail())
                .withClaim("password", request.getPassword())
                .withClaim("name", request.getName())
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }

    @Getter
    @Builder
    public static class Request {
        private final Long memberId;
        private final String email;
        private final String name;
        private final String password;
    }
}
