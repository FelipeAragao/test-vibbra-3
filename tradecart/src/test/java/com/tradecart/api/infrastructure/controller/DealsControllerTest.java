package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.deal.DealService;
import com.tradecart.api.domain.model.Deal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DealsControllerTest {

    @Mock
    private DealService dealService;

    @InjectMocks
    private DealsController dealsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDealById_DealExists() {
        Deal deal = new Deal();
        deal.setId(1L);
        when(dealService.getById(1L)).thenReturn(deal);

        ResponseEntity<Deal> response = dealsController.getDealById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deal, response.getBody());
        verify(dealService, times(1)).getById(1L);
    }

    @Test
    public void testGetDealById_DealNotFound() {
        when(dealService.getById(1L)).thenReturn(null);

        ResponseEntity<Deal> response = dealsController.getDealById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(dealService, times(1)).getById(1L);
    }

    @Test
    public void testCreateDeal_Success() {
        Deal deal = new Deal();
        when(dealService.save(deal)).thenReturn(deal);

        ResponseEntity<Deal> response = dealsController.create(deal);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deal, response.getBody());
        verify(dealService, times(1)).save(deal);
    }

    @Test
    public void testCreateDeal_BadRequest() {
        Deal deal = new Deal();
        when(dealService.save(deal)).thenThrow(new RuntimeException());

        ResponseEntity<Deal> response = dealsController.create(deal);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(dealService, times(1)).save(deal);
    }

    @Test
    public void testUpdateDeal_DealExists() {
        Deal deal = new Deal();
        deal.setId(1L);
        when(dealService.update(1L, deal)).thenReturn(deal);

        ResponseEntity<Deal> response = dealsController.update(1L, deal);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deal, response.getBody());
        verify(dealService, times(1)).update(1L, deal);
    }

    @Test
    public void testUpdateDeal_DealNotFound() {
        Deal deal = new Deal();
        when(dealService.update(1L, deal)).thenReturn(null);

        ResponseEntity<Deal> response = dealsController.update(1L, deal);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(dealService, times(1)).update(1L, deal);
    }

    @Test
    public void testUpdateDeal_BadRequest() {
        Deal deal = new Deal();
        when(dealService.update(1L, deal)).thenThrow(new RuntimeException());

        ResponseEntity<Deal> response = dealsController.update(1L, deal);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(dealService, times(1)).update(1L, deal);
    }
}
