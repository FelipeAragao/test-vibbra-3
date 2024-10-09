package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.bid.BidService;
import com.tradecart.api.domain.model.Bid;
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

public class BidControllerTest {

    @Mock
    private BidService bidService;

    @InjectMocks
    private BidController bidController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBidById_BidExists() {
        long bidId = 1L;
        Bid bid = new Bid();
        bid.setId(bidId);
        when(bidService.getById(bidId)).thenReturn(bid);

        ResponseEntity<Bid> response = bidController.getDealById(bidId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bid, response.getBody());
        verify(bidService, times(1)).getById(bidId);
    }

    @Test
    public void testGetBidById_BidNotFound() {
        long bidId = 1L;
        when(bidService.getById(bidId)).thenReturn(null);

        ResponseEntity<Bid> response = bidController.getDealById(bidId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(bidService, times(1)).getById(bidId);
    }

    @Test
    public void testGetBidById_BadRequest() {
        long bidId = 1L;
        when(bidService.getById(bidId)).thenThrow(new RuntimeException());

        ResponseEntity<Bid> response = bidController.getDealById(bidId);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(bidService, times(1)).getById(bidId);
    }

    @Test
    public void testGetBidsByDeal_BidsExist() {
        long dealId = 1L;
        List<Bid> bids = new ArrayList<>();
        bids.add(new Bid());
        when(bidService.getByDeal(dealId)).thenReturn(bids);

        ResponseEntity<List<Bid>> response = bidController.getByDeal(dealId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bids, response.getBody());
        verify(bidService, times(1)).getByDeal(dealId);
    }

    @Test
    public void testGetBidsByDeal_BidsNotFound() {
        long dealId = 1L;
        when(bidService.getByDeal(dealId)).thenReturn(new ArrayList<>());

        ResponseEntity<List<Bid>> response = bidController.getByDeal(dealId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(bidService, times(1)).getByDeal(dealId);
    }

    @Test
    public void testGetBidsByDeal_BadRequest() {
        long dealId = 1L;
        when(bidService.getByDeal(dealId)).thenThrow(new RuntimeException());

        ResponseEntity<List<Bid>> response = bidController.getByDeal(dealId);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(bidService, times(1)).getByDeal(dealId);
    }

    @Test
    public void testCreateBid_Success() {
        Bid bid = new Bid();
        when(bidService.save(bid)).thenReturn(bid);

        ResponseEntity<Bid> response = bidController.create(bid);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bid, response.getBody());
        verify(bidService, times(1)).save(bid);
    }

    @Test
    public void testCreateBid_BadRequest() {
        Bid bid = new Bid();
        when(bidService.save(bid)).thenThrow(new RuntimeException());

        ResponseEntity<Bid> response = bidController.create(bid);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(bidService, times(1)).save(bid);
    }

    @Test
    public void testUpdateBid_BidExists() {
        long bidId = 1L;
        Bid bid = new Bid();
        bid.setId(bidId);
        when(bidService.update(bidId, bid)).thenReturn(bid);

        ResponseEntity<Bid> response = bidController.update(bidId, bid);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bid, response.getBody());
        verify(bidService, times(1)).update(bidId, bid);
    }

    @Test
    public void testUpdateBid_BidNotFound() {
        long bidId = 1L;
        Bid bid = new Bid();
        when(bidService.update(bidId, bid)).thenReturn(null);

        ResponseEntity<Bid> response = bidController.update(bidId, bid);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(bidService, times(1)).update(bidId, bid);
    }

    @Test
    public void testUpdateBid_BadRequest() {
        long bidId = 1L;
        Bid bid = new Bid();
        when(bidService.update(bidId, bid)).thenThrow(new RuntimeException());

        ResponseEntity<Bid> response = bidController.update(bidId, bid);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(bidService, times(1)).update(bidId, bid);
    }
}
