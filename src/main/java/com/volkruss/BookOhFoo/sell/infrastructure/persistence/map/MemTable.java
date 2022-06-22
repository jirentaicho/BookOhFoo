package com.volkruss.BookOhFoo.sell.infrastructure.persistence.map;

import com.volkruss.BookOhFoo.sell.infrastructure.model.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * テーブルとテーブルの操作を提供します
 * コンストラクタにてテーブルの初期化をしてください
 *
 * @param <T> エンティティクラス
 */
@Deprecated
public abstract class MemTable<T extends Record> {

    // テーブル
    protected List<T> records = new ArrayList<>();

    public void add(T entity){
        this.records.add(entity);
    }

    public void addAll(List<T> entities){
        this.records.addAll(entities);
    }

    public List<T> getRecords(){
        return this.records.stream().collect(Collectors.toList());
    }

}