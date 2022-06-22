package com.volkruss.BookOhFoo.system.application.usecase.csv;

import lombok.Builder;

@Builder
public class SellDetailCsvDto {

    public String bookId;

    public int count;
}
