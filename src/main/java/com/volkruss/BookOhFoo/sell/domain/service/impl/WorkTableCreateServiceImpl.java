package com.volkruss.BookOhFoo.sell.domain.service.impl;

import com.volkruss.BookOhFoo.sell.domain.model.sell.Sell;
import com.volkruss.BookOhFoo.sell.domain.model.sell.SellRepository;
import com.volkruss.BookOhFoo.sell.domain.service.WorkTableCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class WorkTableCreateServiceImpl implements WorkTableCreateService {

    @Autowired
    @Qualifier("SellRepositoryMap")
    private SellRepository sellRepository;

    @Override
    public void createWork(Sell sell) {
        sellRepository.storeWorkHead(sell);
        sellRepository.storeWorkDetail(sell.getSellId(), sell.getDetails());
        // sellRepository.storeWorkDetail(sell.getSellId(), sell.getDetails());
    }
}
