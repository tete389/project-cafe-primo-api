package com.example.cafebackend.Config;

import com.example.cafebackend.Config.token.TokenFilterConfigurer;
import com.example.cafebackend.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenService tokenService;

    private final String[] PUBIC = {
            "/prod/**",
            "/cate/**",
            "/add/**",
            "/type/**",
            "/mate/**",
            "/order/**",
    };

    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(PUBIC).anonymous().anyRequest().authenticated()
                .and().apply(new TokenFilterConfigurer(tokenService));
    }


}
