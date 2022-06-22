package com.volkruss.BookOhFoo.auth.infrastructure.persistence.map.user;

import com.volkruss.BookOhFoo.auth.domain.model.LoginUserDto;
import com.volkruss.BookOhFoo.auth.domain.model.UserRepository;
import com.volkruss.BookOhFoo.auth.infrastructure.model.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("UserRepositoryMap")
public class UserRepositoryMap implements UserRepository {

    private UserTable userTable = UserTable.getInstance();

    @Override
    public LoginUserDto findUserByUsername(String username) {
        List<UserEntity> entities = userTable.getTableManager().getRecords();
        return entities.stream()
                .filter(i -> i.getName().equals(username))
                .map(this::toDto)
                .findFirst()
                .get();
    }

    private LoginUserDto toDto(UserEntity entity){
         LoginUserDto dto = new LoginUserDto();
         dto.setName(entity.getName());
         dto.setPassword(entity.getPassword());
         return dto;
    }

}
