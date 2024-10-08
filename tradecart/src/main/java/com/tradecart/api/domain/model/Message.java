package com.tradecart.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message extends BaseEntity {

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "deal_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Deal deal;

    @Column
    private String title;

    @Column
    private String message;
}
