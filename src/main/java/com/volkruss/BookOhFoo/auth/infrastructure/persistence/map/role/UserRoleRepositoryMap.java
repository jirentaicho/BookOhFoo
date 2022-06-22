package com.volkruss.BookOhFoo.auth.infrastructure.persistence.map.role;

import com.volkruss.BookOhFoo.auth.domain.model.UserRoleRepositroy;
import com.volkruss.BookOhFoo.auth.infrastructure.model.role.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("UserRoleRepositoryMap")
public class UserRoleRepositoryMap implements UserRoleRepositroy {

    private RoleTable roleTable = RoleTable.getInstance();

    @Override
    public List<String> findUserRoleByUsername(String username) {
        List<RoleEntity> roles = roleTable.getTableManager().getRecords();
        return roles.stream()
                .filter(i -> i.getUsername().equals(username))
                .map(RoleEntity::getRole)
                .collect(Collectors.toList());
    }

}
