package com.volkruss.BookOhFoo.sell.usecase.output;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RegistViewOutput {
    private String sellNo;
    private int amt;
    private int campaign;
    private List<RegistViewDetail> list;
    // private Map<String,Integer> list;

    public static class RegistViewDetail{
        public String name;
        public int price;
        public int count;
        public RegistViewDetail(String name,int price,int count){
            this.name = name;
            this.price = price;
            this.count = count;
        }
    }
}
