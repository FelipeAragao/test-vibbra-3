package com.tradecart.api.infrastructure.controller;

import com.tradecart.api.application.service.bid.BidService;
import com.tradecart.api.application.service.message.MessageService;
import com.tradecart.api.domain.model.Bid;
import com.tradecart.api.domain.model.Message;
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

    @PostMapping
    public ResponseEntity<Message> create(Message message){
        try {
            Message savedMessage = service.save(message);
            return ResponseEntity.ok(savedMessage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

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
