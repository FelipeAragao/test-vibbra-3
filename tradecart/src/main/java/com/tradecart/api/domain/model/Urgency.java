package com.tradecart.api.domain.model;

import com.tradecart.api.domain.enums.UrgencyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "urgency")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Urgency extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private UrgencyType type;

    @Column(name = "limit_date")
    private LocalDate limitDate;


}
