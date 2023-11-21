package ybe.mini.travelserver.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ybe.mini.travelserver.domain.member.entity.Member;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record SignupResponse(
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String name
) implements Serializable {
    public static SignupResponse fromEntity(Member member) {
        return new SignupResponse(
                member.getEmail(),
                member.getPassword(),
                member.getName()
        );
    }
}