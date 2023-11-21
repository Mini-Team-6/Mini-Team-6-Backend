package ybe.mini.travelserver.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ybe.mini.travelserver.domain.member.entity.Member;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DeleteResponse(
        @Email
        String email,
        @NotBlank
        String password
) implements Serializable {
    public static DeleteResponse fromEntity(Member member) {
        return new DeleteResponse(
                member.getEmail(),
                member.getPassword()
        );
    }
}