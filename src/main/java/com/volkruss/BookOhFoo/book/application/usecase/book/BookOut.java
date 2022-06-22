package com.volkruss.BookOhFoo.book.application.usecase.book;

import lombok.AllArgsConstructor;

/**
 * 返却用のBookDTO
 */
@AllArgsConstructor
public class BookOut {
    public String bookId;
    public String title;
    public int price;
}
