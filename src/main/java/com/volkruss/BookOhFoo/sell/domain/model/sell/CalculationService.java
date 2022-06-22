package com.volkruss.BookOhFoo.sell.domain.model.sell;

import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import com.volkruss.BookOhFoo.book.domain.model.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculationService {

    @Autowired
    @Qualifier("BookRepositoryMap")
    private BookRepository bookRepository;

    public int calcTotalAmt(List<Detail> details){
        int amt = 0;
        for(Detail detail : details){
            Book book = bookRepository.findById(detail.getBookId());
            amt += (book.getPrice().getValue() * detail.getCount());
        }
        return amt;
    }

}
