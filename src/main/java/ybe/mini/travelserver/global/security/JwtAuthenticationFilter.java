package ybe.mini.travelserver.global.security;

import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final JwtToPrincipalConverter jwtToPrincipalConverter;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String accessToken = jwtService.extractTokenFromRequest(request);

        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        DecodedJWT decodedJWT;

        try {
            decodedJWT = jwtService.decode(accessToken);
        } catch (JWTDecodeException ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
            return;
        }

        try {
            decodedJWT = jwtService.verify(decodedJWT);
            signAuthentication(decodedJWT);
        } catch (TokenExpiredException ex) {
            handleTokenExpired(decodedJWT, response);
        } catch (
                SignatureGenerationException |
                AlgorithmMismatchException |
                InvalidClaimException |
                SignatureVerificationException ex
        ) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void handleTokenExpired(DecodedJWT decodedJWT, HttpServletResponse response) {
        String refreshToken = jwtService.getRefreshToken(decodedJWT.getId());

        if (refreshToken != null) {
            DecodedJWT decodedRefreshToken = jwtService.decode(refreshToken);
            String newAccessToken = jwtService.createAccessToken(
                    decodedRefreshToken.getClaim("email").asString(),
                    decodedRefreshToken.getId()
            );
            response.setHeader("Authorization", "Bearer " + newAccessToken);
            signAuthentication(decodedRefreshToken);
        } else {
            SecurityContextHolder.clearContext();
        }
    }

    private void signAuthentication(DecodedJWT decodedJWT) {
        PrincipalDetails principalDetails = jwtToPrincipalConverter.convert(decodedJWT);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        principalDetails,
                        null,
                        principalDetails.getAuthorities()
                )
        );
    }
}