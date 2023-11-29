package ybe.mini.travelserver.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MypageUpdateRequest(
        @NotBlank
        String password,
        @NotBlank
        String name
) implements Serializable {
}