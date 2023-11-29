package ybe.mini.travelserver.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MypageDeleteRequest(
        @Email
        String email
) implements Serializable {
}