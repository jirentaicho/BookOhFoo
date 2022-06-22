package com.volkruss.BookOhFoo.sell.infrastructure.persistence.map.config;

import com.volkruss.BookOhFoo.sell.infrastructure.model.NumberingEntity;
import com.volkruss.BookOhFoo.utils.database.ListTableManager;

import java.util.ArrayList;
import java.util.List;

public class NumberingTable {

    private static List<NumberingEntity> records = new ArrayList<>();

    private static NumberingTable instance = new NumberingTable();

    private ListTableManager tableManager;

    private NumberingTable(){
        records.add(new NumberingEntity(1,0));
        this.tableManager = new ListTableManager<NumberingEntity>(records);
    }

    public ListTableManager getTableManager(){
        return this.tableManager;
    }

    public static NumberingTable getInstance(){
        return instance;
    }
}
