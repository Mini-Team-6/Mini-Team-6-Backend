package ybe.mini.travelserver.global.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties properties;
    private final StringRedisTemplate redisTemplate;

    public String createAccessToken(String email, String uuid) {
        var now = Instant.now();

        String accessToken = JWT.create()
                .withClaim("email", email)
                .withIssuedAt(now)
                .withJWTId(uuid)
                .withIssuer(properties.getIssuer())
                .withExpiresAt(now.plus(properties.getAccessTokenDuration()))
                .sign(Algorithm.HMAC256(properties.getSecretKey()));

        log.warn("신규 액세스 토큰이 발급되었습니다: {}", accessToken);

        return accessToken;
    }

    public String createRefreshToken(String email, String uuid) {
        return JWT.create()
                .withClaim("email", email)
                .withJWTId(uuid)
                .withIssuer(properties.getIssuer())
                .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }

    public void saveRefreshToken(String jwtId, String token) {
        redisTemplate.opsForValue().set(jwtId, token, properties.getRefreshTokenDuration());
    }

    public String getRefreshToken(String jwtId) {
        return redisTemplate.opsForValue().get(jwtId);
    }

    public void deleteRefreshToken(String jwtId) {
        redisTemplate.delete(jwtId);
    }

    public DecodedJWT decode(String token) {
        return JWT.decode(token);
    }

    public DecodedJWT verify(DecodedJWT token) {
        return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))
                .build()
                .verify(token);
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

}
