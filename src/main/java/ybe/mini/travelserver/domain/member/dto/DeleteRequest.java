package ybe.mini.travelserver.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ybe.mini.travelserver.domain.member.entity.Member;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DeleteRequest(
        @Email
        String email,
        @NotBlank
        String password
) implements Serializable {
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}