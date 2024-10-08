package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.bid.BidService;
import com.tradecart.api.domain.model.Bid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deals/{deal_id}/bids")
public class BidController {

    private final BidService service;

    public BidController(BidService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bid> getDealById(@PathVariable("id") long id) {
        try {
            Bid bid = service.getById(id);
            if (bid == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(bid);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Bid>> getByDeal(@PathVariable("id") long id) {
        try {
            List<Bid> bids = service.getByDeal(id);
            if (bids == null || bids.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(bids);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Bid> create(Bid bid){
        try {
            Bid savedBid = service.save(bid);
            return ResponseEntity.ok(savedBid);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bid> update(@PathVariable("id") long id, @RequestBody Bid bid){
        try {
            Bid updatedBid = service.update(id, bid);
            if (updatedBid == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedBid);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
