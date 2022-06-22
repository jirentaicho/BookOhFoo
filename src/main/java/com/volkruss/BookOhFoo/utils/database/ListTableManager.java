package com.volkruss.BookOhFoo.utils.database;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * リストで表現するテーブルを操作するクラスです
 *
 * @param <T> 行構造を持つクラス
 */
public class ListTableManager<T> {

    private List<T> table;

    public ListTableManager(List<T> table){
        // 参照を代入します
        this.table = table;
    }

    public List<T> getRecords(){
        return this.table.stream().collect(Collectors.toList());
    }

    public void add(T entity){
        this.table.add(entity);
    }

    public void addAll(List<T> records){
        this.table.addAll(records);
    }

    public void remove(T entity){
        this.table.remove(entity);
    }

}