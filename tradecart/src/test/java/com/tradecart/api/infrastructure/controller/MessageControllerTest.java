package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.message.MessageService;
import com.tradecart.api.domain.model.Message;
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

class MessageControllerTest {
    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDealById_MessageExists() {
        Message message = new Message();
        message.setId(1L);
        when(messageService.getById(1L)).thenReturn(message);

        ResponseEntity<Message> response = messageController.getDealById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
        verify(messageService, times(1)).getById(1L);
    }

    @Test
    public void testGetDealById_MessageNotFound() {
        when(messageService.getById(1L)).thenReturn(null);

        ResponseEntity<Message> response = messageController.getDealById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(messageService, times(1)).getById(1L);
    }

    @Test
    public void testGetByDeal_MessagesExist() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message());
        when(messageService.getByDeal(1L)).thenReturn(messages);

        ResponseEntity<List<Message>> response = messageController.getByDeal(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messages, response.getBody());
        verify(messageService, times(1)).getByDeal(1L);
    }

    @Test
    public void testGetByDeal_MessagesNotFound() {
        when(messageService.getByDeal(1L)).thenReturn(new ArrayList<>());

        ResponseEntity<List<Message>> response = messageController.getByDeal(1L);


        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(messageService, times(1)).getByDeal(1L);
    }

    @Test
    public void testCreateMessage_Success() {
        Message message = new Message();
        when(messageService.save(message)).thenReturn(message);

        ResponseEntity<Message> response = messageController.create(message);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
        verify(messageService, times(1)).save(message);
    }

    @Test
    public void testUpdateMessage_MessageExists() {
        Message message = new Message();
        message.setId(1L);
        when(messageService.update(1L, message)).thenReturn(message);

        ResponseEntity<Message> response = messageController.update(1L, message);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
        verify(messageService, times(1)).update(1L, message);
    }

    @Test
    public void testUpdateMessage_MessageNotFound() {
        Message message = new Message();
        when(messageService.update(1L, message)).thenReturn(null);

        ResponseEntity<Message> response = messageController.update(1L, message);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(messageService, times(1)).update(1L, message);
    }
}