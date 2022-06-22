package com.volkruss.BookOhFoo.sell.infrastructure.persistence.map.sell;

import com.volkruss.BookOhFoo.sell.infrastructure.model.sell.SellDetailWorkEntity;
import com.volkruss.BookOhFoo.utils.database.ListTableManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellDetailWorkTable {

    // テーブルを表現します
    private static List<SellDetailWorkEntity> records = new ArrayList<>();

    private static SellDetailWorkTable instance = new SellDetailWorkTable();

    // 共通の操作を行うクラス
    private ListTableManager<SellDetailWorkEntity> tableManager;

    public ListTableManager getTableManager(){
        return this.tableManager;
    }

    private SellDetailWorkTable(){
        this.tableManager = new ListTableManager<>(records);
    }

    public static SellDetailWorkTable getInstance(){
        return instance;
    }

    public List<SellDetailWorkEntity> getRecords(){
        return SellDetailWorkTable.records.stream().collect(Collectors.toList());
    }


}
