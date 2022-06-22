package com.volkruss.BookOhFoo.sell.usecase.impl;

import com.volkruss.BookOhFoo.sell.domain.model.sell.Detail;
import com.volkruss.BookOhFoo.sell.domain.model.sell.Sell;
import com.volkruss.BookOhFoo.sell.domain.model.sell.SellRepository;
import com.volkruss.BookOhFoo.system.application.usecase.csv.SellCsvDto;
import com.volkruss.BookOhFoo.system.domain.service.csv.SellCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SellCsvServiceImpl implements SellCsvService {

    @Autowired
    @Qualifier("SellRepositoryMap")
    private SellRepository sellRepository;

    @Override
    public List<SellCsvDto> getSellCsv() {
        List<Sell> sells = this.sellRepository.findAll();
        List<SellCsvDto> dtos = new ArrayList<>();
        for(Sell sell : sells) {
            dtos.addAll(this.toSellCsvDtoList(sell));
        }
        return dtos;
    }

    /**
     * SellからList<SellCsvDto>を作成するメソッドを作成
     *
     * @param sell
     * @return
     */
    private List<SellCsvDto> toSellCsvDtoList(Sell sell){
        List<SellCsvDto> dtos = new ArrayList<>();
        for(Detail detail : sell.getDetails()){
            SellCsvDto csv = SellCsvDto.builder()
                    .sellId(sell.getSellId().getId())
                    .name(sell.getSellerName())
                    .campaign(sell.getCampaignAmt())
                    .amt(sell.getAmount())
                    .way(sell.getWay())
                    .phoneNumber(sell.getPhoneNumber())
                    .bookId(detail.getBookIdStr())
                    .count(detail.getCount())
                    .build();
            dtos.add(csv);
        }
        return dtos;
    }

}
