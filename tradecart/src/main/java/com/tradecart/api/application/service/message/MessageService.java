package com.tradecart.api.application.service.message;

import com.tradecart.api.domain.model.Bid;
import com.tradecart.api.domain.model.Message;
import com.tradecart.api.domain.repository.BidRepository;
import com.tradecart.api.domain.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repository;

    private MessageService(MessageRepository repository){
        this.repository = repository;
    }

    public Message getById(long id){
        return repository.findById(id).orElse(null);
    }

    public List<Message> getByDeal(long dealId) {
        return repository.findByDealId(dealId);
    }

    public Message save(Message deal) {
        return repository.save(deal);
    }

    public Message update(long id, Message bid) {
        Message existingMessage = repository.findById(id).orElse(null);
        if (existingMessage == null) {
            return null;
        }
        return repository.save(bid);
    }
}
