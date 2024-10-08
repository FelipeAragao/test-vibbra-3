package com.tradecart.api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "deals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Deal extends BaseEntity {

    @Column(name = "deal_value")
    private double value;

    @Column
    private String description;

    @Column
    private String tradeFor;

    @JoinColumn(name = "location_id")
    @ManyToOne
    private Location location;

    @Column
    private String photos;
}
