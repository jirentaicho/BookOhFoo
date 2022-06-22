package com.volkruss.BookOhFoo.book.domain.model.book;

import java.util.List;

public interface BookRepository {
    // Bookを1件取得する
    Book findById(BookId bookId);
    // 該当するBookIdのレコードを取得する
    List<Book> findByBookIdIn(List<String> bookIds);
    // 全件取得する
    List<Book> findAll();

    void store(Book book);

    void update(Book book);

    void delete(Book book);
}
