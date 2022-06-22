package com.volkruss.BookOhFoo.sell.domain.model.sell;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SellTest {

    @Test
    public void test_calcQuantity(){
        Sell sell = this.getSell();
        sell.registerDetail(getDetails());
        int count = sell.calcQuantity();
        assertEquals(count,30);
    }

    private Sell getSell(){
        Sell sell = new Sell("testuser",987,"TestWay","TestSell",0,0);
        return sell;
    }

    private List<Detail> getDetails(){
        return List.of(
                this.getDetail("Test01",10),
                this.getDetail("Test02",10),
                this.getDetail("Test03",10)
        );
    }

    private Detail getDetail(String bookId,int count){
        return new Detail(bookId,count,0);
    }

}