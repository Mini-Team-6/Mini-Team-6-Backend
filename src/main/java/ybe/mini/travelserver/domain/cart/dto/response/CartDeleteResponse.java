package ybe.mini.travelserver.domain.cart.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CartDeleteResponse (
        Long id
){
}