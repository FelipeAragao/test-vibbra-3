package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.user.UserService;
import com.tradecart.api.domain.model.User;
import com.tradecart.api.domain.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository repository;

    private final UserService service;

    public UserController(UserRepository repository, UserService service)
    {
        this.repository = repository;
        this.service = service;
    }

    @Operation(summary = "Get user by id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = service.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Update user")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User updatedUser = service.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

}
