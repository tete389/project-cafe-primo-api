package com.example.cafebackend.config.token;

import com.example.cafebackend.service.TokenService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenService tokenService;

    public TokenFilterConfigurer(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        TokenFilter filter = new TokenFilter(tokenService);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
