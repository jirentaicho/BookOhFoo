package com.volkruss.BookOhFoo.auth.infrastructure.persistence.map.role;

import com.volkruss.BookOhFoo.auth.infrastructure.model.role.RoleEntity;
import com.volkruss.BookOhFoo.utils.database.ListTableManager;
import com.volkruss.BookOhFoo.utils.date.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ログインユーザが持っている権限を管理しているRoleテーブルです
 */
public class RoleTable {
    // テーブルを表現します
    private static List<RoleEntity> records = new ArrayList<>();

    private static RoleTable instance = new RoleTable();

    private RoleTable(){
        String createdAt = DateUtil.getCurrentStr();
        this.records.add(new RoleEntity(1,"misaka","システム管理者"));
        this.records.add(new RoleEntity(2,"misaka","在庫管理者"));
        this.records.add(new RoleEntity(3,"shokuhou","在庫管理者"));
        this.records.add(new RoleEntity(4,"mugino","システム管理者"));

        this.tableManager = new ListTableManager<RoleEntity>(records);
    }

    // 共通の操作を行うクラス
    private ListTableManager tableManager;

    public ListTableManager getTableManager(){
        return this.tableManager;
    }

    public static RoleTable getInstance(){
        return instance;
    }

}
