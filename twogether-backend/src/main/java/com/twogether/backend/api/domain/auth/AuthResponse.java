package com.twogether.backend.api.domain.auth;

public class AuthResponse {
    @SuppressWarnings("unused")
    public String token;

    public AuthResponse(final String token) {
        this.token = token;
    }
}