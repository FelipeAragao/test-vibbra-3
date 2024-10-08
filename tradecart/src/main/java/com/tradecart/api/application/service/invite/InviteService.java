package com.tradecart.api.application.service.invite;

import com.tradecart.api.domain.model.Invite;
import com.tradecart.api.domain.model.Message;
import com.tradecart.api.domain.repository.InviteRepository;
import com.tradecart.api.domain.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InviteService {

    private final InviteRepository repository;

    private InviteService(InviteRepository repository){
        this.repository = repository;
    }

    public Invite getById(long id){
        return repository.findById(id).orElse(null);
    }

    public Invite getByuserIdAndId(long userId, long id){
        return repository.findByUserIdAndId(userId, id).orElse(null);
    }

    public List<Invite> getByUser(long userId) {
        return repository.findByUserId(userId);
    }

    public Invite save(Invite deal) {
        return repository.save(deal);
    }

    public Invite update(long id, Invite bid) {
        Invite existingMessage = repository.findById(id).orElse(null);
        if (existingMessage == null) {
            return null;
        }
        return repository.save(bid);
    }
}
