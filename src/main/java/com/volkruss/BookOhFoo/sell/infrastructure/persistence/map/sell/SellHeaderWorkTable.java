package com.volkruss.BookOhFoo.sell.infrastructure.persistence.map.sell;

import com.volkruss.BookOhFoo.sell.infrastructure.model.sell.SellHeaderWorkEntity;
import com.volkruss.BookOhFoo.utils.database.ListTableManager;
import com.volkruss.BookOhFoo.utils.date.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class SellHeaderWorkTable{

    // テーブルを表現します
    private static List<SellHeaderWorkEntity> records = new ArrayList<>();

    private static SellHeaderWorkTable instance = new SellHeaderWorkTable();

    private SellHeaderWorkTable(){
        String createdAt = DateUtil.getCurrentStr();
        this.records.add(new SellHeaderWorkEntity(1,"Test1","渋谷かのん",111,11,1,"CASH",createdAt));
        this.records.add(new SellHeaderWorkEntity(2,"Test2","御坂美琴",111,11,1,"CASH",createdAt));
        this.records.add(new SellHeaderWorkEntity(3,"Test3","平安名すみれ",111,11,1,"BANK",createdAt));
        this.records.add(new SellHeaderWorkEntity(4,"Test4","佐天涙子",111,11,1,"BANK",createdAt));

        this.tableManager = new ListTableManager<>(records);
    }

    // 共通の操作を行うクラス
    private ListTableManager<SellHeaderWorkEntity> tableManager;

    public ListTableManager getTableManager(){
        return this.tableManager;
    }

    public static SellHeaderWorkTable getInstance(){
        return instance;
    }

}
