package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.user.UserService;
import com.tradecart.api.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.tradecart.api.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserById_UserExists() {
        User user = new User();
        user.setId(1L);
        when(userService.getUserById(1L)).thenReturn(user);


        ResponseEntity<User> response = userController.getUserById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        when(userService.getUserById(1L)).thenReturn(null);

        ResponseEntity<User> response = userController.getUserById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testUpdateUser_UserExists() {
        User user = new User();
        user.setId(1L);
        when(userService.updateUser(1L, user)).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(1L, user);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).updateUser(1L, user);
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        User user = new User();
        when(userService.updateUser(1L, user)).thenReturn(null);

        ResponseEntity<User> response = userController.updateUser(1L, user);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService, times(1)).updateUser(1L, user);
    }
}