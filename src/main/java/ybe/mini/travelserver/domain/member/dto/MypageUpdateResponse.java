package ybe.mini.travelserver.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record MypageUpdateResponse(
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String name
) implements Serializable {
    public static MypageUpdateResponse fromEntity(ybe.mini.travelserver.domain.member.entity.Member member) {
        return new MypageUpdateResponse(
                member.getEmail(),
                member.getPassword(),
                member.getName()
        );
    }
}