package com.volkruss.BookOhFoo.book.infrastructure.persistence.map.book;

import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import com.volkruss.BookOhFoo.book.domain.model.book.BookId;
import com.volkruss.BookOhFoo.book.domain.model.book.Price;
import com.volkruss.BookOhFoo.book.infrastructure.model.book.BookEntity;
import com.volkruss.BookOhFoo.utils.database.MapTableManager;

import java.util.HashMap;
import java.util.Map;

public class BookTable {

    private static Map<String,BookEntity> records = new HashMap<>();

    private static BookTable instance = new BookTable();

    private MapTableManager tableManager;

    public MapTableManager<BookEntity> getTableManager(){
        return this.tableManager;
    }

    private BookTable(){
        BookTable.records.put("BOOK001",new BookEntity(1,"BOOK001",300,"Spring入門"));
        BookTable.records.put("BOOK002",new BookEntity(2,"BOOK002",1000,"Haskell入門"));
        BookTable.records.put("BOOK003",new BookEntity(3,"BOOK003",100,"はじめてのPerl"));
        BookTable.records.put("BOOK004",new BookEntity(4,"BOOK004",700,"Vue.js入門"));
        BookTable.records.put("BOOK005",new BookEntity(5,"BOOK005",2000,"Laravel入門"));

        this.tableManager = new MapTableManager<>(records);
    }

    public static BookTable getInstance(){
        return instance;
    }

    private Book toBook(BookEntity entity){
        Book book = new Book(
            entity.getBookId(),
            entity.getPrice(),
            entity.getTitle());
        return book;
    }

}
