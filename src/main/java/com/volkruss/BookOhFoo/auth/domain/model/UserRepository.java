package com.volkruss.BookOhFoo.auth.domain.model;

import java.util.Map;

public interface UserRepository {
    LoginUserDto findUserByUsername(String username);
}
