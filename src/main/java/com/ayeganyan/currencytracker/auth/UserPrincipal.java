package com.ayeganyan.currencytracker.auth;

import com.ayeganyan.currencytracker.model.AuthGroupEntity;
import com.ayeganyan.currencytracker.model.CredentialEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import static java.util.stream.Collectors.*;

public class UserPrincipal implements UserDetails {
    private final CredentialEntity credentialEntity;
    private final Collection<AuthGroupEntity> authGroupEntities;

    public UserPrincipal(CredentialEntity credentialEntity, Collection<AuthGroupEntity> authGroupEntities) {
        super();
        this.credentialEntity = credentialEntity;
        this.authGroupEntities = authGroupEntities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authGroupEntities != null ?
                authGroupEntities.stream()
                        .map(ag -> new SimpleGrantedAuthority(ag.getAuthGroup()))
                        .collect(toSet()) :
                Collections.emptySet();
    }

    @Override
    public String getPassword() {
        return credentialEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return credentialEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
