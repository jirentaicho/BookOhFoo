package com.volkruss.BookOhFoo.sell.domain.model.sell;

import com.volkruss.BookOhFoo.book.domain.model.book.Price;

import java.util.Objects;

public abstract class CampaignDecorator implements Campaign{
    private Campaign campaign;

    public CampaignDecorator(Campaign campaign){
        this.campaign = campaign;
    }

    public Price applyCampaign(Sell sell){
        Price price = new Price(0);
        if(Objects.nonNull(this.campaign)){
            price.add(this.campaign.apply(sell));
        }
        return price;
    }

}
