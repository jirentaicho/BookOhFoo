package com.volkruss.BookOhFoo.auth.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    private String name;
    private String password;
}
