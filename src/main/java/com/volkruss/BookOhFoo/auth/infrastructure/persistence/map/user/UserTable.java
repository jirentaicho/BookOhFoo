package com.volkruss.BookOhFoo.auth.infrastructure.persistence.map.user;

import com.volkruss.BookOhFoo.auth.infrastructure.model.user.UserEntity;
import com.volkruss.BookOhFoo.utils.database.ListTableManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.List;

/**
 * ログインユーザの情報を管理しているUserテーブルです
 */
public class UserTable {
    private static List<UserEntity> records = new ArrayList<>();

    private static UserTable instance = new UserTable();

    private UserTable(){
        PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        records.add(new UserEntity(1,"misaka",pe.encode("mikoto")));
        records.add(new UserEntity(1,"shokuho", pe.encode("misaki")));
        records.add(new UserEntity(1,"mugino",pe.encode("sizuri")));
        records.add(new UserEntity(1,"sogita",pe.encode("gunha")));

        this.tableManager = new ListTableManager<UserEntity>(records);
    }

    private ListTableManager tableManager;

    public ListTableManager getTableManager(){
        return this.tableManager;
    }

    public static UserTable getInstance(){
        return instance;
    }
}
