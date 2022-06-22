package com.volkruss.BookOhFoo.sell.domain.model.sell;

/**
 * 一つの伝票が持つ一意の識別子
 * 伝票と明細はこの識別子を使って紐づけられます
 */
public class SellId {

    private String id;

    public SellId(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
