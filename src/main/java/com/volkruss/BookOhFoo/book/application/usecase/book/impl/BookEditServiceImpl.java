package com.volkruss.BookOhFoo.book.application.usecase.book.impl;

import com.volkruss.BookOhFoo.book.application.usecase.book.BookEditService;
import com.volkruss.BookOhFoo.book.application.usecase.book.BookOut;
import com.volkruss.BookOhFoo.book.controller.BookRegisterForm;
import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import com.volkruss.BookOhFoo.book.domain.model.book.BookId;
import com.volkruss.BookOhFoo.book.domain.model.book.BookRepository;
import com.volkruss.BookOhFoo.book.domain.model.book.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookEditServiceImpl implements BookEditService {

    @Autowired
    @Qualifier("BookRepositoryMap")
    private BookRepository bookRepository;

    @Override
    public List<BookOut> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        return books.stream().map(this::toOut).collect(Collectors.toList());
    }

    @Override
    public BookOut getBookByBookId(String bookId) {
        Book book = this.bookRepository.findById(new BookId(bookId));
        return this.toOut(book);
    }

    @Override
    public void update(BookRegisterForm bookRegisterForm) {
        Book book = this.bookRepository.findById(new BookId(bookRegisterForm.getBookId()));
        book.updateInfo(bookRegisterForm.getPrice(),bookRegisterForm.getTitle());
        this.bookRepository.update(book);
    }

    @Override
    public void delete(String bookId) {
        Book book = this.bookRepository.findById(new BookId(bookId));
        // TODO チェック処理など
        this.bookRepository.delete(book);
    }

    private BookOut toOut(Book book){
        return new BookOut(book.getBookIdStr(),book.getTitle(),book.getPrice().getValue());
    }

}
