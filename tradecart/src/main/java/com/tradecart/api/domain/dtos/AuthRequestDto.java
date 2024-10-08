package com.tradecart.api.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {
    private String login;
    private String password;
}
