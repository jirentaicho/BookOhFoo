package com.volkruss.BookOhFoo.book.domain.model.book;

public class BookId {
    private String bookId;

    public BookId(String bookId){
        this.bookId = bookId;
    }

    public String getId(){
        return this.bookId;
    }

    @Override
    public int hashCode(){
        int hash =
                + this.bookId.hashCode();
        return hash;
    }
}
