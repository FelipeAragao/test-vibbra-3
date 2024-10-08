package com.tradecart.api.application.service.deal;

import com.tradecart.api.domain.model.Deal;
import com.tradecart.api.domain.repository.DealsRepository;
import org.springframework.stereotype.Service;

@Service
public class DealService {

    DealsRepository repository;

    private DealService(DealsRepository repository) {
        this.repository = repository;
    }

    public Deal getById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Deal save(Deal deal) {
        return repository.save(deal);
    }

    public Deal update(long id, Deal deal) {
        Deal existingDeal = repository.findById(id).orElse(null);
        if (existingDeal == null) {
            return null;
        }
        return repository.save(deal);
    }
}
