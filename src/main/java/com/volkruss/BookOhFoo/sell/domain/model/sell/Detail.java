package com.volkruss.BookOhFoo.sell.domain.model.sell;

import com.volkruss.BookOhFoo.book.domain.model.book.Book;
import com.volkruss.BookOhFoo.book.domain.model.book.BookId;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Detail {
    // private Book book;
    private BookId bookId;
    private int count;
    private int detailNo; // TODO implements

    public Detail(String bookId,int count,int detailNo){
        this.bookId = new BookId(bookId);
        this.count = count;
        this.detailNo = detailNo;
    }

    public BookId getBookId(){ return this.bookId; }
    public String getBookIdStr(){
        return this.bookId.getId();
    }
}
