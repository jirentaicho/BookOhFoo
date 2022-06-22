package com.volkruss.BookOhFoo.sell.usecase.output;

import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseViewOutput {
    private String bookId;
    private String title;
    private int price;

    public static PurchaseViewOutput toOut(Book book){
        return new PurchaseViewOutput(book.getBookIdStr(),book.getTitle(), book.getPrice().getValue());
    }
}
