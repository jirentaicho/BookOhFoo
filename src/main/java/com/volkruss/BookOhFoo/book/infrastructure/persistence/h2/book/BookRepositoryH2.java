package com.volkruss.BookOhFoo.book.infrastructure.persistence.h2.book;

import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import com.volkruss.BookOhFoo.book.domain.model.book.BookId;
import com.volkruss.BookOhFoo.book.domain.model.book.BookRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRepositoryH2 implements BookRepository {

    @Override
    public Book findById(BookId bookId) {
        return null;
    }

    @Override
    public List<Book> findByBookIdIn(List<String> bookIds) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void store(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Book book) {

    }

}
