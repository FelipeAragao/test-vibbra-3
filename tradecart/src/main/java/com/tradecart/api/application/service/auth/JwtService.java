package com.tradecart.api.application.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tradecart.api.domain.model.User;
import com.tradecart.api.domain.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtService {

    private final UserRepository repository;

    private JwtService(UserRepository repository){
        this.repository = repository;
    }

    private final String key = "BYvwUGwtQuih13SkIfX94j8HuXwjxnMy";

    public String generateToken(User user) {
        Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);

        return JWT.create()
                .withSubject(user.getLogin())
                .withClaim("userId", user.getId())
                .withExpiresAt(expirationDate)
                .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(key));
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(key)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(key)).build().verify(token);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public Authentication getAuthentication(String token) {
        String userLogin = getUserLoginFromToken(token);
        Optional<User> user =repository.findByLogin(userLogin);
        if (user.isEmpty()) {
            return null;
        }

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return user.get().getPassword();
            }

            @Override
            public String getUsername() {
                return userLogin;
            }
        };

        return new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>());
    }

    public String getUserLoginFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token)
                .getSubject();
    }
}
