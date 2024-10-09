package com.tradecart.api.infrastructure.auth;

import com.tradecart.api.application.service.auth.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults() )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/api/v1/authenticate",
                                "/api/v1/deals/{deal_id}/bids [GET]",
                                "/api/v1/deals/{deal_id}/bids/{id} [GET]",
                                "/api/v1/deals/{deal_id}/messages [GET]",
                                "/api/v1/deals/{deal_id}/messages/{id} [GET]"
                                ).permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(new JwtAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
