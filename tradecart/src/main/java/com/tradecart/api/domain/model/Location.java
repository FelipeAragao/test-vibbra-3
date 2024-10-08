package com.tradecart.api.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "locations")
class Location extends BaseEntity {

    @Column(unique = true)
    private double lat;

    @Column
    private double lng;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private int zipCode;
}
