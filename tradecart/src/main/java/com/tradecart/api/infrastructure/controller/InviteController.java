package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.deal.DealService;
import com.tradecart.api.application.service.invite.InviteService;
import com.tradecart.api.application.service.user.UserService;
import com.tradecart.api.domain.model.Deal;
import com.tradecart.api.domain.model.Invite;
import com.tradecart.api.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{user_id}/invites")
public class InviteController {

    private final InviteService service;
    private final UserService userService;

    public InviteController(InviteService service, UserService userService)
    {
        this.service = service;
        this.userService = userService;
    }

    @Operation(summary = "Get invites by user")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping
    public ResponseEntity<List<Invite>> getUserById(@PathVariable("user_id") long userId) {
        List<Invite> invites = service.getByUser(userId);
        if (invites == null || invites.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(invites);
    }

    @Operation(summary = "Get invite by id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Invite not found")
    @GetMapping("/{id}")
    public ResponseEntity<Invite> getUserById(@PathVariable("user_id") long userId, @PathVariable("id") long id) {
        Invite invite = service.getByuserIdAndId(userId, id);
        if (invite == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(invite);
    }

    @Operation(summary = "Create invite")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping
    public ResponseEntity<Invite> create(@PathVariable("user_id") long userId, @RequestBody Invite invite){
        try {
            if (invite.getUser() == null){
                User user = this.userService.getUserById(userId);
                invite.setUser(user);
            }
            Invite savedInvite = service.save(invite);
            return ResponseEntity.ok(savedInvite);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update invite")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Invite not found")
    @PutMapping("/{id}")
    public ResponseEntity<Invite> update(@PathVariable("id") long id, @RequestBody Invite invite){
        try {
            Invite updatedInvite = service.update(id, invite);
            if (updatedInvite == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedInvite);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
