package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.invite.InviteService;
import com.tradecart.api.application.service.user.UserService;
import com.tradecart.api.domain.model.Invite;
import com.tradecart.api.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InviteControllerTest {

    @Mock
    private InviteService inviteService;

    @Mock
    private UserService userService;

    @InjectMocks
    private InviteController inviteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById_InvitesExist() {
        long userId = 1L;
        List<Invite> invites = new ArrayList<>();
        invites.add(new Invite());
        when(inviteService.getByUser(userId)).thenReturn(invites);

        ResponseEntity<List<Invite>> response = inviteController.getUserById(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(invites, response.getBody());
        verify(inviteService, times(1)).getByUser(userId);
    }

    @Test
    public void testGetUserById_InvitesNotFound() {
        long userId = 1L;
        when(inviteService.getByUser(userId)).thenReturn(new ArrayList<>());

        ResponseEntity<List<Invite>> response = inviteController.getUserById(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(inviteService, times(1)).getByUser(userId);
    }

    @Test
    public void testGetInviteById_InviteExists() {
        long userId = 1L;
        long inviteId = 1L;
        Invite invite = new Invite();
        invite.setId(inviteId);
        when(inviteService.getByuserIdAndId(userId, inviteId)).thenReturn(invite);

        ResponseEntity<Invite> response = inviteController.getUserById(userId, inviteId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(invite, response.getBody());
        verify(inviteService, times(1)).getByuserIdAndId(userId, inviteId);
    }

    @Test
    public void testGetInviteById_InviteNotFound() {
        long userId = 1L;
        long inviteId = 1L;
        when(inviteService.getByuserIdAndId(userId, inviteId)).thenReturn(null);

        ResponseEntity<Invite> response = inviteController.getUserById(userId, inviteId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(inviteService, times(1)).getByuserIdAndId(userId, inviteId);
    }

    @Test
    public void testCreateInvite_Success() {
        long userId = 1L;
        Invite invite = new Invite();
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(user);
        when(inviteService.save(invite)).thenReturn(invite);

        ResponseEntity<Invite> response = inviteController.create(userId, invite);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(invite, response.getBody());
        verify(inviteService, times(1)).save(invite);
    }

    @Test
    public void testCreateInvite_BadRequest() {
        long userId = 1L;
        Invite invite = new Invite();
        when(userService.getUserById(userId)).thenReturn(new User());
        when(inviteService.save(invite)).thenThrow(new RuntimeException());

        ResponseEntity<Invite> response = inviteController.create(userId, invite);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(inviteService, times(1)).save(invite);
    }

    @Test
    public void testUpdateInvite_InviteExists() {
        long inviteId = 1L;
        Invite invite = new Invite();
        invite.setId(inviteId);
        when(inviteService.update(inviteId, invite)).thenReturn(invite);

        ResponseEntity<Invite> response = inviteController.update(inviteId, invite);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(invite, response.getBody());
        verify(inviteService, times(1)).update(inviteId, invite);
    }

    @Test
    public void testUpdateInvite_InviteNotFound() {
        long inviteId = 1L;
        Invite invite = new Invite();
        when(inviteService.update(inviteId, invite)).thenReturn(null);

        ResponseEntity<Invite> response = inviteController.update(inviteId, invite);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(inviteService, times(1)).update(inviteId, invite);
    }

    @Test
    public void testUpdateInvite_BadRequest() {
        long inviteId = 1L;
        Invite invite = new Invite();
        when(inviteService.update(inviteId, invite)).thenThrow(new RuntimeException());

        ResponseEntity<Invite> response = inviteController.update(inviteId, invite);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(inviteService, times(1)).update(inviteId, invite);
    }
}
