package com.volkruss.BookOhFoo.sell.domain.service.impl;

import com.volkruss.BookOhFoo.book.domain.model.book.Price;
import com.volkruss.BookOhFoo.sell.domain.model.sell.Sell;
import com.volkruss.BookOhFoo.sell.domain.service.CampaignService;
import org.springframework.stereotype.Component;

@Deprecated
@Component
public class CampaignServiceImpl implements CampaignService {
    @Override
    public Price getCampaignValue(Sell sell) {
        Price price = new Price(0);
        int amt = sell.getAmount();
        if(amt > 1000){
            price.add(new Price((int)amt / 5));
        }
        return price;
    }
}
