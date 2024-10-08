package com.tradecart.api.infrastructure.auth;

import com.tradecart.api.application.service.auth.AuthenticationService;
import com.tradecart.api.application.service.auth.JwtService;
import com.tradecart.api.domain.dtos.AuthRequestDto;
import com.tradecart.api.domain.dtos.AuthResponseDto;
import com.tradecart.api.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }


    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody AuthRequestDto authRequest) {
        try {
            User user = authenticationService.authenticate(authRequest.getLogin(), authRequest.getPassword());
            String token = jwtService.generateToken(user);
            return ResponseEntity.ok(new AuthResponseDto(token, user));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid credentials"));
        }
    }
}
