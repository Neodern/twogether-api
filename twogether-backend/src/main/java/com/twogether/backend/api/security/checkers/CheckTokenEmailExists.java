package com.twogether.backend.api.security.checkers;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CheckTokenEmailExists implements ClaimSecurityChecker {

	@Override
	public boolean matchRequest(HttpServletRequest request) {
		return true;
	}
	
	@Override
	public boolean checkClaimAuthorized(HttpServletRequest request, Claims claims) {
		return true;
	}
}
