package com.tradecart.api.application.service.auth;

import com.tradecart.api.domain.model.User;
import com.tradecart.api.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String login, String password) throws AuthenticationException {

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Invalid credentials");
        }

        return user;
    }
}
