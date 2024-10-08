package com.tradecart.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "bids")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bid extends BaseEntity {

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "deal_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Deal deal;

    @Column
    private boolean accepted;

    @Column(name = "bid_value")
    private double value;

    @Column
    private String description;
}
