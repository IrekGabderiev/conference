package com.irek.conference.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    LISTENER, ADMIN, PRESENTER;

    @Override
    public String getAuthority() {
        return name();
    }
}
