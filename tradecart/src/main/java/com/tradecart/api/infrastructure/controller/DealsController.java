package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.deal.DealService;
import com.tradecart.api.domain.model.Deal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deals")
public class DealsController {

    private final DealService service;

    public DealsController(DealService service) {
        this.service = service;
    }

    @Operation(summary = "Get deal by id")
    @ApiResponse(responseCode = "200", description = "Deal found")
    @ApiResponse(responseCode = "404", description = "Deal not found")
    @GetMapping("/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable("id") long id) {
        Deal deal = service.getById(id);
        if (deal == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deal);
    }

    @Operation(summary = "Create deal")
    @ApiResponse(responseCode = "200", description = "Deal created")
    @PostMapping
    public ResponseEntity<Deal> create(Deal deal){
        try {
            Deal savedDeal = service.save(deal);
            return ResponseEntity.ok(savedDeal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update deal")
    @ApiResponse(responseCode = "200", description = "Deal updated")
    @ApiResponse(responseCode = "404", description = "Deal not found")
    @PutMapping("/{id}")
    public ResponseEntity<Deal> update(@PathVariable("id") long id, @RequestBody Deal deal){
        try {
            Deal updatedDeal = service.update(id, deal);
            if (updatedDeal == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedDeal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
