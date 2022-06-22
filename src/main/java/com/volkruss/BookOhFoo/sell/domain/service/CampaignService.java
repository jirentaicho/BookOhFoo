package com.volkruss.BookOhFoo.sell.domain.service;

import com.volkruss.BookOhFoo.book.domain.model.book.Price;
import com.volkruss.BookOhFoo.sell.domain.model.sell.Sell;

@Deprecated
public interface CampaignService {
    Price getCampaignValue(Sell sell);
}
