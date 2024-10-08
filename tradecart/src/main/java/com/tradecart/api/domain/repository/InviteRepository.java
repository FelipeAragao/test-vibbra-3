package com.tradecart.api.domain.repository;

import com.tradecart.api.domain.model.Invite;
import com.tradecart.api.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {

    List<Invite> findByUserId(long userId);

    Optional<Invite> findById(long id);

    Optional<Invite> findByUserIdAndId(long userId, long id);

}
