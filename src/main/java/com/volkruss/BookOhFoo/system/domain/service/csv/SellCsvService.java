package com.volkruss.BookOhFoo.system.domain.service.csv;

import com.volkruss.BookOhFoo.system.application.usecase.csv.SellCsvDto;

import java.util.List;
// TODO パッケージ考える
public interface SellCsvService {
    List<SellCsvDto> getSellCsv();
}
