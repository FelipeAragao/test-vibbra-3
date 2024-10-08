package com.tradecart.api.domain.repository;

import com.tradecart.api.domain.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    Optional<Bid> findByDealIdAndId(long dealId, long id);

    List<Bid> findByDealId(long dealId);
}
