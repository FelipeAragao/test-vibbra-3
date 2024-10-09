package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.bid.BidService;
import com.tradecart.api.domain.model.Bid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Get bid by id")
    @ApiResponse(responseCode = "200", description = "Bid found")
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

    @Operation(summary = "Get bids by deal")
    @ApiResponse(responseCode = "200", description = "Bids found")
    @GetMapping()
    public ResponseEntity<List<Bid>> getByDeal(@PathVariable("deal_id") long id) {
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

    @Operation(summary = "Create bid")
    @ApiResponse(responseCode = "200", description = "Bid created")
    @PostMapping
    public ResponseEntity<Bid> create(Bid bid){
        try {
            Bid savedBid = service.save(bid);
            return ResponseEntity.ok(savedBid);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update bid")
    @ApiResponse(responseCode = "200", description = "Bid updated")
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
