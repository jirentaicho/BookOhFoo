package com.volkruss.BookOhFoo.sell.usecase.impl;

import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import com.volkruss.BookOhFoo.book.domain.model.book.BookRepository;
import com.volkruss.BookOhFoo.sell.usecase.ShowPurchaseView;
import com.volkruss.BookOhFoo.sell.usecase.output.PurchaseViewOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShowPurchaseViewImpl implements ShowPurchaseView {

    @Autowired
    @Qualifier("BookRepositoryMap")
    private BookRepository bookRepository;

    @Override
    public List<PurchaseViewOutput> showPurchaseView() {
        List<Book> books = this.bookRepository.findAll();
        List<PurchaseViewOutput> outs = books.stream().map(PurchaseViewOutput::toOut).collect(Collectors.toList());
        return outs;
    }
}
