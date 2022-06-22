package com.volkruss.BookOhFoo.book.domain.model.book;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

@Getter
public class Book {
    private BookId bookId;
    private Price price;
    private String title;

    public Book(String bookId, int price, String title){
        this.bookId = new BookId(bookId);
        this.price = new Price(price);
        this.title = title;
    }

    public void updateInfo(int price, String title){
        this.price = new Price(price);
        this.title = title;
    }

    public String getBookIdStr(){
        return this.bookId.getId();
    }

    @Override
    public int hashCode(){
        int hashcode = bookId.hashCode();
        return hashcode;
    }

    @Override
    public boolean equals(Object anObject){
        boolean equal = false;
        if(anObject != null && this.getClass() == anObject.getClass()){
            Book book = (Book) anObject;
            equal = book.bookId.getId().equals(this.bookId.getId());
        }
        return equal;
    }

    @Override
    public String toString(){
        return "Book [BookId = " + this.bookId + ", price = " + this.price.getValue() + ", title = " + this.title;
    }
}
