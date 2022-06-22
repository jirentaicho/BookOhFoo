package com.volkruss.BookOhFoo.sell.domain.model.sell;

import com.volkruss.BookOhFoo.book.domain.model.book.Price;

public class DefaultCampaign implements Campaign{
    @Override
    public Price apply(Sell sell) {
        Price price = new Price(0);
        // 買取合計価格が1000円以上で50円アップ
        if(sell.getAmount() >= 1000){
            price.add(new Price(50));
        }
        return price;
    }
}
