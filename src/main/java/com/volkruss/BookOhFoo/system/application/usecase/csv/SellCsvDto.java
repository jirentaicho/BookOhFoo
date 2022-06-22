package com.volkruss.BookOhFoo.system.application.usecase.csv;


import lombok.Builder;

import java.util.List;

@Builder
public class SellCsvDto {
    public String sellId;

    public String name;

    public String way;

    public int campaign;

    public int amt;

    public int phoneNumber;

    public List<SellDetailCsvDto> details;

    public String bookId;

    public int count;
}
