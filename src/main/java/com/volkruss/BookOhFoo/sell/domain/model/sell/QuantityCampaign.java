package com.volkruss.BookOhFoo.sell.domain.model.sell;

import com.volkruss.BookOhFoo.book.domain.model.book.Price;

public class QuantityCampaign extends CampaignDecorator {

    public QuantityCampaign(Campaign campaign) {
        super(campaign);
    }

    @Override
    public Price apply(Sell sell) {
        // 既存のCampaignを実施する
        Price price = applyCampaign(sell);

        // 独自のCampaignを実施する
        // 合計買取件数が10以上で100円アップ
        int count = sell.calcQuantity();
        if(count >= 10){
            price.add(new Price(100));
        }
        return price;
    }

}
