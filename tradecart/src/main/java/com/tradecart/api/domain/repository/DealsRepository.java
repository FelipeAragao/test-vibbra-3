package com.tradecart.api.domain.repository;

import com.tradecart.api.domain.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealsRepository extends JpaRepository<Deal, Long> {

}
