package com.volkruss.BookOhFoo.system.application.usecase.csv.impl;

import com.volkruss.BookOhFoo.system.application.usecase.csv.CsvService;
import com.volkruss.BookOhFoo.system.application.usecase.csv.SellCsv;
import com.volkruss.BookOhFoo.system.application.usecase.csv.SellCsvDto;
import com.volkruss.BookOhFoo.system.domain.service.csv.SellCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvServiceImpl implements CsvService {

    // 実装はsellパッケージにありDtoを経由してデータを持ってきます
    @Autowired
    private SellCsvService csvService;

    @Override
    public List<SellCsv> getSellCsv() {

        List<SellCsvDto> dtos = this.csvService.getSellCsv();
        return dtos.stream().map(this::toCsv).collect(Collectors.toList());
    }

    private SellCsv toCsv(SellCsvDto dto){
        SellCsv csv = SellCsv.builder()
                .bookId(dto.bookId)
                .amt(dto.amt)
                .count(dto.count)
                .name(dto.name)
                .phoneNumber(dto.phoneNumber)
                .way(dto.way)
                .campaign(dto.campaign)
                .sellId(dto.sellId)
                .build();
        return csv;
    }

}
