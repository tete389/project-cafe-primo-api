package com.example.cafebackend.config;

import com.example.cafebackend.config.token.TokenFilterConfigurer;
import com.example.cafebackend.service.TokenService;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware {
    private final TokenService tokenService;

    private final String[] PUBIC = {
            "/ws/**",
            "/product/**",
            "/category/**",
            "/addOn/**",
            "/material/**",
            "/option/**",

            "/customer/**",
            "/employee/register",
            "/employee/login",
            "/employee/getSetting",
            "/order/createOrder",
            "/order/updateProductInOrder",
            "/order/getOrderById",
            "/order/getRecentOrder",
            "/order/getEmployeeNotifications",
            "/order/getCustomerNotifications",
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
        http.cors(config -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowCredentials(true);
            // cors.setAllowedOriginPatterns(Collections.singletonList("http://*"));
            cors.setAllowedOriginPatterns(Arrays.asList("http://*", "https://*"));
            // cors.addAllowedOriginPattern("https://*");
            cors.addAllowedHeader("*");
            cors.addAllowedMethod("POST");
            cors.addAllowedMethod("GET");
            cors.addAllowedMethod("PUT");
            cors.addAllowedMethod("DELETE");
            cors.addAllowedMethod("OPTIONS");

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", cors);

            config.configurationSource(source);
        }).csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(PUBIC).anonymous().anyRequest().authenticated()
                .and().apply(new TokenFilterConfigurer(tokenService));
    }

    // @Bean
    // public CorsFilter corsFilter() {
    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // CorsConfiguration config = new CorsConfiguration();
    // config.setAllowCredentials(true);
    // config.setAllowedOriginPatterns(List.of("*"));
    // // config.setAllowedOrigins(Arrays.asList("*"));
    // // config.setAllowedHeaders(Arrays.asList("*"));
    // // config.setAllowedOriginPatterns(Collections.singletonList("*"));
    // config.addAllowedHeader("*");
    // // config.addAllowedOrigin("/*");
    // // config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS",
    // // "DELETE"));
    // config.addAllowedMethod("OPTIONS");
    // config.addAllowedMethod("POST");
    // config.addAllowedMethod("GET");
    // config.addAllowedMethod("PUT");
    // config.addAllowedMethod("DELETE");
    // source.registerCorsConfiguration("/**", config);
    // return new CorsFilter(source);
    // }

}
