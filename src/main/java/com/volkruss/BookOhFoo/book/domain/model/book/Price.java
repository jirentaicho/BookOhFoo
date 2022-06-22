package com.volkruss.BookOhFoo.book.domain.model.book;

public class Price {
    private int amt;

    public Price(int price){
        if(price < 0){
            // TODO エラー
        }
        this.amt = price;
    }

    public int getValue(){
        return this.amt;
    }

    public Price add(Price price){
        this.amt += price.getValue();
        return new Price(this.amt);
    }

    public Price sub(Price price){
        this.amt -= price.getValue();
        return new Price(this.amt);
    }

}
