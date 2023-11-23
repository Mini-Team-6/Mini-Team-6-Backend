package ybe.mini.travelserver.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ybe.mini.travelserver.domain.member.entity.Member;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MypageDeleteResponse(
        @Email
        String email,
        @NotBlank
        String password
) implements Serializable {
    public static MypageDeleteResponse fromEntity(Member member) {
        return new MypageDeleteResponse(
                member.getEmail(),
                member.getPassword()
        );
    }
}