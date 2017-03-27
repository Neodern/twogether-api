package com.twogether.backend.api.security.checkers;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

public interface ClaimSecurityChecker {

    boolean matchRequest(HttpServletRequest request);

    boolean checkClaimAuthorized(HttpServletRequest request, Claims claims);
}
