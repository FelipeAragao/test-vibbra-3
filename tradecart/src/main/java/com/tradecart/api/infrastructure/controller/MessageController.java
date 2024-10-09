package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.bid.BidService;
import com.tradecart.api.application.service.message.MessageService;
import com.tradecart.api.domain.model.Bid;
import com.tradecart.api.domain.model.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deals/{deal_id}/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @Operation(summary = "Get deal by id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Deal not found")
    @GetMapping("/{id}")
    public ResponseEntity<Message> getDealById(@PathVariable("id") long id) {
        try {
            Message message = service.getById(id);
            if (message == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(message);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get messages by deal")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Deal not found")
    @GetMapping()
    public ResponseEntity<List<Message>> getByDeal(@PathVariable("id") long id) {
        try {
            List<Message> messages = service.getByDeal(id);
            if (messages == null || messages.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(messages);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Create message")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping
    public ResponseEntity<Message> create(Message message){
        try {
            Message savedMessage = service.save(message);
            return ResponseEntity.ok(savedMessage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update message")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Message not found")
    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") long id, @RequestBody Message message){
        try {
            Message updatedMessage = service.update(id, message);
            if (updatedMessage == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedMessage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
