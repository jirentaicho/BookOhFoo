package com.volkruss.BookOhFoo.sell.usecase.impl;

import com.volkruss.BookOhFoo.sell.domain.model.sell.Sell;
import com.volkruss.BookOhFoo.sell.domain.model.sell.SellId;
import com.volkruss.BookOhFoo.sell.domain.model.sell.SellRepository;
import com.volkruss.BookOhFoo.sell.usecase.SellSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SellSubmitServiceImpl implements SellSubmitService {

    @Autowired
    @Qualifier("SellRepositoryMap")
    private SellRepository sellRepository;

    @Override
    public void submit(String sellId) {
        SellId registSellId = new SellId(sellId);
        Sell sell = sellRepository.findSellBySellId(registSellId);

        this.sellRepository.storeHead(sell);
        this.sellRepository.storeDetail(registSellId,sell.getDetails());
    }
}
