package com.volkruss.BookOhFoo.sell.infrastructure.persistence.map.sell;

import com.volkruss.BookOhFoo.utils.database.ListTableManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellDetailTable {

    // テーブルを表現します
    private static List<SellDetailTable> records = new ArrayList<>();

    private static SellDetailTable instance = new SellDetailTable();

    // 共通の操作を行うクラス
    private ListTableManager<SellDetailTable> tableManager;

    public ListTableManager getTableManager(){
        return this.tableManager;
    }

    private SellDetailTable(){
        this.tableManager = new ListTableManager<>(records);
    }

    public static SellDetailTable getInstance(){
        return instance;
    }

    public List<SellDetailTable> getRecords(){
        return SellDetailTable.records.stream().collect(Collectors.toList());
    }


}
