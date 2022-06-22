package com.volkruss.BookOhFoo.auth.domain.model;

import java.util.List;

public interface UserRoleRepositroy {
    List<String> findUserRoleByUsername(String username);
}
