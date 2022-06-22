package com.volkruss.BookOhFoo.book.application.usecase.book.impl;

import com.volkruss.BookOhFoo.book.application.usecase.book.BookRegistService;
import com.volkruss.BookOhFoo.book.controller.BookRegisterForm;
import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import com.volkruss.BookOhFoo.book.domain.model.book.BookId;
import com.volkruss.BookOhFoo.book.domain.model.book.BookRepository;
import com.volkruss.BookOhFoo.book.domain.model.book.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BookRegistServiceImpl implements BookRegistService {

    @Autowired
    @Qualifier("BookRepositoryMap")
    private BookRepository bookRepository;

    @Override
    public void registerNewBook(BookRegisterForm bookRegisterForm) {
        Book book = new Book(
                bookRegisterForm.getBookId(),
                bookRegisterForm.getPrice(),
                bookRegisterForm.getTitle()
        );
        this.bookRepository.store(book);
    }
}
