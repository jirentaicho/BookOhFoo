package com.volkruss.BookOhFoo.sell.domain.model.sell;

import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import com.volkruss.BookOhFoo.book.domain.model.book.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculationServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private CalculationService calculationService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_calc(){
        Book book = new Book("Test001",10,"TestBook");
        when(this.bookRepository.findById(any())).thenReturn(book);

        int amt = this.calculationService.calcTotalAmt(this.getTestDetail(5,10,15));
        assertEquals(300,amt);
    }

    private List<Detail> getTestDetail(int count1,int count2, int count3){
        return List.of(
                new Detail("Test001",count1,0),
                new Detail("Test002",count2,0),
                new Detail("Test003",count3,0)
        );
    }

}