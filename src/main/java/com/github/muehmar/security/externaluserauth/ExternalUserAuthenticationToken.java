package com.github.muehmar.security.externaluserauth;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public record ExternalUserAuthenticationToken(ExternalUser user, Collection<GrantedAuthority> authorities) implements Authentication {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return user().name();
    }
}
