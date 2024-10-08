package com.tradecart.api.application.service.bid;

import com.tradecart.api.domain.model.Bid;
import com.tradecart.api.domain.repository.BidRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {

    private final BidRepository repository;

    private BidService(BidRepository repository){
        this.repository = repository;
    }

    public Bid getById(long id){
        return repository.findById(id).orElse(null);
    }

    public List<Bid> getByDeal(long dealId) {
        return repository.findByDealId(dealId);
    }

    public Bid save(Bid deal) {
        return repository.save(deal);
    }

    public Bid update(long id, Bid bid) {
        Bid existingBid = repository.findById(id).orElse(null);
        if (existingBid == null) {
            return null;
        }
        return repository.save(bid);
    }
}
