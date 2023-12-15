package ybe.mini.travelserver.global.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtProperties {
    private String secretKey;
    private String issuer;
    private Duration accessTokenDuration;
    private Duration refreshTokenDuration;
}
