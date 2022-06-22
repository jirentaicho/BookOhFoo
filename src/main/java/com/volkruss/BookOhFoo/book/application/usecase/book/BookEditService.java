package com.volkruss.BookOhFoo.book.application.usecase.book;

import com.volkruss.BookOhFoo.book.controller.BookRegisterForm;

import java.util.List;

public interface BookEditService {
    List<BookOut> getAllBooks();
    BookOut getBookByBookId(String bookId);
    void update(BookRegisterForm bookRegisterForm);
    void delete(String bookId);
}
