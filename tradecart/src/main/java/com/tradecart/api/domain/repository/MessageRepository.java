package com.tradecart.api.domain.repository;

import com.tradecart.api.domain.model.Bid;
import com.tradecart.api.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByDealIdAndId(long dealId, long id);

    List<Message> findByDealId(long dealId);
}
