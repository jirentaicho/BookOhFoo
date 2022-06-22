package com.volkruss.BookOhFoo.sell.domain.model.sell;

import com.volkruss.BookOhFoo.book.domain.model.book.Price;
@FunctionalInterface
public interface CampaignMethod<T> {
    Price applyCampaign(T f);
}
