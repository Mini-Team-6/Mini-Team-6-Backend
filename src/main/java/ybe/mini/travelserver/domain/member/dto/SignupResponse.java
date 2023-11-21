package ybe.mini.travelserver.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ybe.mini.travelserver.domain.member.entity.Member;

import java.io.Serializable;

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