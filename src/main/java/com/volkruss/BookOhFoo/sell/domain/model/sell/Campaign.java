package com.volkruss.BookOhFoo.sell.domain.model.sell;

import com.volkruss.BookOhFoo.book.domain.model.book.Price;

public interface Campaign {
    Price apply(Sell sell);
}