package ybe.mini.travelserver.global.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {
    private final PrincipalDetails principalDetails;

    public UserPrincipalAuthenticationToken(PrincipalDetails principalDetails) {
        super(principalDetails.getAuthorities());
        this.principalDetails = principalDetails;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public PrincipalDetails getPrincipal() {
        return principalDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserPrincipalAuthenticationToken that = (UserPrincipalAuthenticationToken) o;

        return principalDetails.equals(that.principalDetails);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + principalDetails.hashCode();
        return result;
    }
}
