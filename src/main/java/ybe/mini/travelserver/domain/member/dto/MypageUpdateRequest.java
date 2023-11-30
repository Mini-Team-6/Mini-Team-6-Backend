package ybe.mini.travelserver.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MypageUpdateRequest(
        String password,
        String name
) implements Serializable {
}