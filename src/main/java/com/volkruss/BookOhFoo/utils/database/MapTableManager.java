package com.volkruss.BookOhFoo.utils.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public final class MapTableManager<T> {

    private Map<String, T> table;

    public MapTableManager(Map<String, T> table){
        // 参照を代入します
        this.table = table;
    }

    // Listの形式にして返却します
    public List<T> getTableOfList(){
        // Map(value) → List
        return new ArrayList<>(this.table.values());
    }

    public Map<String, T> getTable(){
        return this.table;
    }

    public T findById(String key){
        return this.table.get(key);
    }

    public void add(String key, T entity){
        this.table.put(key,entity);
    }

    public void addAll(Map<String,T> records){
        this.table.putAll(records);
    }

    public void remove(String key){
        this.table.remove(key);
    }
}
