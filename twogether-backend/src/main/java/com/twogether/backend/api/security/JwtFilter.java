package com.twogether.backend.api.security;

import com.twogether.backend.api.security.checkers.ClaimSecurityChecker;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtFilter extends GenericFilterBean {

    private static final String JWT_PREFIX = "Bearer ";
    private String secretKey;

    private List<ClaimSecurityChecker> securityCheckers;


    public JwtFilter(String secretKey, List<ClaimSecurityChecker> securityCheckers) {
        super();
        this.secretKey = secretKey;
        this.securityCheckers = securityCheckers;
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith(JWT_PREFIX)) {
            ((HttpServletResponse) res).sendError(HttpStatus.UNAUTHORIZED.value(), "Missing or invalid Authorization header.");
            return;
        }

        final String token = authHeader.substring(JWT_PREFIX.length());

        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            // On vérifie que toutes les règles de securité ne sont pas violées
            for(ClaimSecurityChecker checker : securityCheckers){
                if (checker.matchRequest(request) && !checker.checkClaimAuthorized(request, claims)){
                    ((HttpServletResponse) res).sendError(HttpStatus.UNAUTHORIZED.value(), "Token not compliant with request path");
                    return;
                }
            }

            request.setAttribute("claims", claims);
        } catch (SignatureException e) {
            ((HttpServletResponse) res).sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token.");
        }

        chain.doFilter(req, res);
    }

}
