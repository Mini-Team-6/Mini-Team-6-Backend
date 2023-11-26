package ybe.mini.travelserver.global.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Role {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String HAS_ROLE_USER = "hasRole('ROLE_USER')";
}