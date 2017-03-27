package com.twogether.backend.api.config;

import com.twogether.backend.api.security.JwtFilter;
import com.twogether.backend.api.security.checkers.ClaimSecurityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class JwtConfig {

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Autowired
    List<ClaimSecurityChecker> securityCheckers;

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter(secretKey, securityCheckers));
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
