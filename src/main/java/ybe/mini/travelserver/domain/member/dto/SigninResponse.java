package ybe.mini.travelserver.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SigninResponse(
        @NotBlank
        String token
) implements Serializable {
    public static SigninResponse fromEntity(String token) {
        return new SigninResponse(token);
    }
}