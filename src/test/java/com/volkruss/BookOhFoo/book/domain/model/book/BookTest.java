package com.volkruss.BookOhFoo.book.domain.model.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    public void test(){
        Book book = new Book("Test",0,"TestTitle");
        book.updateInfo(1000,"UpdateTitle");
        assertEquals(1000,book.getPrice().getValue());
        assertEquals("UpdateTitle",book.getTitle());
    }

}